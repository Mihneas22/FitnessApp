package com.example.fiicodenou.features.domain.util

import java.lang.Exception

sealed class Resource<out T> {
    data class Succes<out T>(val result: T): Resource<T>()
    data class Failure(val ex: Exception): Resource<Nothing>()
    data object Loading: Resource<Nothing>()
}