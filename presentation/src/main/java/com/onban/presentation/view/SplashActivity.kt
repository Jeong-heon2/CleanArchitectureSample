package com.onban.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onban.presentation.R
import com.onban.presentation.di.PresentationComponentProvider
import com.onban.presentation.viewmodel.SplashViewModel
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        inject()
        viewModel.fetchStartEvent()
        TestS
    }

    private fun inject() {
        (applicationContext as PresentationComponentProvider).providePresentationComponent().inject(this)
    }
}