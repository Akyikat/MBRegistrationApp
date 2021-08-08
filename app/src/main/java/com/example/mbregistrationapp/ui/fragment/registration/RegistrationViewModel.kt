package com.example.mbregistrationapp.ui.fragment.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mbregistrationapp.data.model.Phone
import com.example.mbregistrationapp.repository.RegistrationRepository
import com.example.mbregistrationapp.storage.AppPreferences
import com.example.mbregistrationapp.utils.ResponseResult
import io.reactivex.disposables.CompositeDisposable

class RegistrationViewModel(private val repository: RegistrationRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    val availableCountries: MutableLiveData<ResponseResult<List<String>>> = MutableLiveData()
    val checkPhone: MutableLiveData<ResponseResult<PhoneState>> = MutableLiveData()

    fun getCountries() {
        availableCountries.value = ResponseResult.loading()
        val token = AppPreferences.token
        if (token != null) {
            disposable.add(
                repository.getAvailableCountries(token).subscribe({
                    val countriesName = mutableListOf<String>()
                    it.result?.forEach { countries ->
                        countriesName.add(countries.name)
                    }
                    availableCountries.postValue(ResponseResult.success(countriesName))
                }, {
                    availableCountries.postValue(ResponseResult.error(it.message))
                })
            )
        }
    }

    fun checkNumber(phone: String) {
        checkPhone.value = ResponseResult.loading()
        val token = AppPreferences.token
        if (token != null) {
            disposable.add(
                repository.checkPhone(Phone(phone), token).subscribe({
                    if (it.result != null) {
                        checkPhone.postValue(ResponseResult.success(PhoneState.NOT_EXIST))
                    }
                    if (it.error != null) {
                        checkPhone.postValue(ResponseResult.success(PhoneState.EXIST))
                    }
                }, {
                    checkPhone.postValue(ResponseResult.error(it.message))
                })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}

enum class PhoneState {
    EXIST, NOT_EXIST
}



