package com.example.mbregistrationapp.data.model

import com.google.gson.annotations.SerializedName

data class CountryList(
    val code: Int,
    val result: List<CountryModel>? = null,
    val message: String? = null
)

data class CountryModel(
    val id: Int,
    val name: String,
    @SerializedName("iso_code")
    val isoCode: String,
    @SerializedName("phone_code")
    val phoneCode: Int,
    @SerializedName("phone_length")
    val phoneLength: Int,
    @SerializedName("phone_mask")
    val phoneMask: String,
    @SerializedName("phone_mask_small")
    val phoneMaskSmall: String
)