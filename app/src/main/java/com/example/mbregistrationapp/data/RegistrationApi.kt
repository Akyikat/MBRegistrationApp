package com.example.mbregistrationapp.data

import com.example.mbregistrationapp.data.model.CheckPhoneModel
import com.example.mbregistrationapp.data.model.CountryList
import com.example.mbregistrationapp.data.model.Phone
import com.example.mbregistrationapp.repository.RegistrationResponse
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface RegistrationApi {

    @POST("listAvailableCountry")
    fun getAvailableCountries(
        @Query("token") token: String
    ): Observable<CountryList>

    @POST("checkPhone")
    @FormUrlEncoded
    fun checkPhone(
        @Field("phone") phone: Phone,
        @Query("token") token: String
    ): Single<CheckPhoneModel>

    @POST("registration")
    fun registration(
        @Query("token") token: String,
        @Body registrationForm: RegistrationForm
    ): Single<RegistrationResponse>

}

data class RegistrationForm(
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("u_data")
    val uDate: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("nationality")
    val nationality: Int,
    @SerializedName("first_phone")
    val firstPhone: String,
    @SerializedName("second_phone")
    val secondPhone: String,
    @SerializedName("traffic_source")
    val trafficSource: Int,
    @SerializedName("question")
    val question: Int,
    @SerializedName("response")
    val response: String,
    @SerializedName("sms_code")
    val smsCode: String,
    @SerializedName("system")
    val system: String
)
