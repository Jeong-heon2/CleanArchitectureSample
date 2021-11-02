package com.onban.remote.adapter

import com.onban.data.model.NetworkResponse
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject

/* https://f2janyway.github.io/android/kotlin/retrofit-suspend-callback/ */
class NetworkResponseAdapter <S:Any,E:Any> @Inject constructor(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
): CallAdapter<S, Call<NetworkResponse<S, E>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> =
        NetworkResponseCall(call,errorBodyConverter)

    internal class NetworkResponseCall<S:Any,E:Any>(
        private val delegate: Call<S>,
        private val errorConverter: Converter<ResponseBody, E>
    ) : Call<NetworkResponse<S, E>> {
        override fun clone(): Call<NetworkResponse<S, E>> =
            NetworkResponseCall(delegate.clone(), errorConverter)

        override fun execute(): Response<NetworkResponse<S, E>> =
            throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")

        override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
            return delegate.enqueue(object : Callback<S> {
                override fun onResponse(call: Call<S>, response: Response<S>) {
                    val body = response.body()
                    val code = response.code()
                    val error = response.errorBody()
                    //Callback<T> 파일 주석을 보면
                    //response가 404 or 500 에러가 있을수 있다고 하니 이를 거르기 위해
                    //if(response.isSuccessful)분기 처리 해줌.

                    //void boolean isSuccessful(){
                    // return code >= 200 && code < 300
                    //}
                    if(response.isSuccessful){
                        if(body != null){
                            callback.onResponse(this@NetworkResponseCall, Response.success(
                                NetworkResponse.Success(body)
                            ))
                        }else{
                            // response is successful but the body is null
                            callback.onResponse(this@NetworkResponseCall, Response.success(
                                NetworkResponse.UnknownError(null)
                            ))
                        }
                    }else{
                        val errorBody = when{
                            error == null -> null
                            error.contentLength() == 0L -> null
                            else ->try{
                                errorConverter.convert(error)
                            }catch (e:Exception) {
                                null
                            }
                        }
                        if(errorBody != null){
                            callback.onResponse(this@NetworkResponseCall, Response.success(
                                NetworkResponse.ApiError(errorBody, code)
                            ))
                        }else{
                            callback.onResponse(this@NetworkResponseCall, Response.success(
                                NetworkResponse.UnknownError(null)
                            ))
                        }
                    }
                }

                override fun onFailure(call: Call<S>, t: Throwable) {
                    val networkResponse = when(t){
                        is IOException -> NetworkResponse.NetworkError(t)
                        else-> NetworkResponse.UnknownError(t)
                    }
                    callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
                }
            })
        }

        override fun isExecuted(): Boolean =delegate.isExecuted

        override fun cancel(): Unit =delegate.cancel()

        override fun isCanceled(): Boolean =delegate.isCanceled

        override fun request(): Request =delegate.request()

        override fun timeout(): Timeout =delegate.timeout()

    }

}

class NetworkResponseAdapterFactory @Inject constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // suspend functions wrap the response type in `Call`
        // retrofit이 suspend func을 처리할때 내부적으로 Call을 처리한다고 한다.
        // 여기서도 그 처리를 해야하기 때문에 returnType이 Call인지 체크한다.
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResponse<<Foo>> or Call<NetworkResponse<out Foo>>"
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not Service then we can't handle this type, so we return null
        if (getRawType(responseType) != NetworkResponse::class.java) {
            return null
        }

        // the response type is Service and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(1, responseType)

        val errorBodyConverter =
            retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
    }
}