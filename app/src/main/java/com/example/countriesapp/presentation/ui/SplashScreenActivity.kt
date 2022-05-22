package com.example.countriesapp.presentation.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.countriesapp.R
import com.example.countriesapp.common.PrefsHelper
import com.example.countriesapp.common.PrefsHelper.accessToken
import com.example.countriesapp.databinding.ActivitySplashScreenBinding
import com.example.countriesapp.util.common.Errors
import com.example.countriesapp.common.error.ErrorMessage
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    @Inject
    lateinit var errorMessage: ErrorMessage
    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: SplashViewModel by viewModels()
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = PrefsHelper.customPreference(this)
        initViewStateObserver()
        accessTokenValidate()
    }

    private fun accessTokenValidate(){
        val accessToken : String = getAccessTokenPreferences()
        if (accessToken.isNullOrEmpty()){
            getAccessToken()
        }else{
            continueWithToken()
        }
    }

    private fun getAccessTokenPreferences() : String {
        return sharedPref.accessToken.toString()
    }

    private fun setAccessTokenPreferences(accessToken: String){
        sharedPref.accessToken = accessToken
    }

    private fun setViewState(viewState: SplashViewState) {
        when (viewState) {
            is ShowError -> showError(viewState.error)
            ShowLoading -> showLoader()
            is Continue -> saveAccessToken(viewState.data)
        }
    }

    private fun saveAccessToken(data: String) {
        setAccessTokenPreferences(data)
        continueProcess()
    }

    private fun continueProcess() {
        hideLoader()
        hideError()
        continueNextActivity()
    }

    private fun initViewStateObserver() {
        viewModel.viewState.observe(this, Observer(::setViewState))
    }

    private fun getAccessToken() {
        hideError()
        viewModel.getAccessToken()
    }


    private fun showLoader() {
        binding.tvLoading.text = getText(R.string.loading)
        binding.pbProgress.visibility = View.VISIBLE
        binding.tvLoading.visibility = View.VISIBLE
        binding.ivMap.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.pbProgress.visibility = View.GONE
        binding.tvLoading.visibility = View.GONE
        binding.ivMap.visibility = View.GONE
    }

    private fun showError(error: Errors) {
        hideLoader()
        val message = errorMessage.getMessage(error)
        binding.vError.root.visibility = View.VISIBLE
        binding.vError.tvErrorMessage.text = message
        binding.vError.btnErrorRetry.setOnClickListener {
            getAccessToken()
        }
    }

    private fun hideError() {
        binding.vError.root.visibility = View.GONE
    }

    private fun continueNextActivity(){
       startActivity()
    }

    private fun continueWithToken(){
        lifecycleScope.launch {
            showLoader()
            delay(1000L)
            startActivity()
        }
    }

    private fun startActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}