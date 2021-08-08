package com.example.mbregistrationapp.repository

import com.example.mbregistrationapp.data.RegistrationApi
import com.example.mbregistrationapp.data.model.CheckPhoneModel
import com.example.mbregistrationapp.data.model.CountryList
import com.example.mbregistrationapp.data.model.Phone
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface RegistrationRepository {

    fun getAvailableCountries(token: String): Observable<CountryList>

    fun checkPhone(phone: Phone, token: String): Single<CheckPhoneModel>

}

class RegistrationRepositoryImpl(private val api: RegistrationApi) : RegistrationRepository {

    override fun getAvailableCountries(token: String): Observable<CountryList> =
        api.getAvailableCountries(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun checkPhone(phone: Phone, token: String): Single<CheckPhoneModel> {
        return api.checkPhone(phone, token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}