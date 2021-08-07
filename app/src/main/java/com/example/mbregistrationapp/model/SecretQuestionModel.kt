package com.example.mbregistrationapp.model

data class SecretQuestionList(
    val code: Int,
    val result: MutableList<SecretQuestionModel>? = null,
    val message: String? = null
)

data class SecretQuestionModel(
    val id: Int,
    val name: String
)
