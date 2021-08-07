package com.example.mbregistrationapp.base

import com.example.mbregistrationapp.data.model.CountryList

sealed class BaseEvent

sealed class RegistrationEvent : BaseEvent() {
    class CountryListFetched(val item: CountryList): RegistrationEvent()
}

