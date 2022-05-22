package com.example.countriesapp.util.common

sealed class Errors {
    object NetworkError : Errors()
    object DatabaseError : Errors()
    object UnknownError : Errors()
}
