package com.example.mbregistrationapp.data.model


data class CheckPhoneModel(
    val code: Int,
    val result: PhoneResult?,
    val error: ResponseError?
)

data class PhoneResult(
    val id: Int
)

data class Phone(val phone: String)