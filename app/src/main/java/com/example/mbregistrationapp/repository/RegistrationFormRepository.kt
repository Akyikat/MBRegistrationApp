package com.example.mbregistrationapp.repository

import com.example.mbregistrationapp.data.RegistrationApi
import com.example.mbregistrationapp.data.RegistrationForm
import com.example.mbregistrationapp.data.model.ResponseError
import io.reactivex.Single

interface RegistrationFormRepository {
    fun registration(
        token: String,
        registrationForm: RegistrationForm
    ): Single<RegistrationResponse>
}

class RegistrationFormRepositoryImpl(private val api: RegistrationApi) :
    RegistrationFormRepository {

    override fun registration(token: String, registrationForm: RegistrationForm): Single<RegistrationResponse> {
        return api.registration(token, registrationForm)
    }

}

data class RegistrationResponse(
    val code: Int,
    val result: RegistrationResult?,
    val error: ResponseError
)

data class RegistrationResult(
    val code: Int?,
    val id: Int?,
    val login: String?,
    val password: String?,
    val message: String?
)