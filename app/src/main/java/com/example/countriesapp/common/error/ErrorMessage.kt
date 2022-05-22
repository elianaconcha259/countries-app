package com.example.countriesapp.common.error

import com.example.countriesapp.util.common.Errors

interface ErrorMessage {
    fun getMessage(error: Errors?): String
}
