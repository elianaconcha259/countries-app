package com.example.countriesapp.data.mapper

import com.example.countriesapp.data.datasource.local.db.entity.CountryEntity
import com.example.countriesapp.data.datasource.remote.network.dto.CountryDTO
import com.example.countriesapp.domain.model.CountryModel


fun CountryDTO.toCountryModel() = CountryModel(
    this.country_name ?: "",
    this.country_short_name ?: "",
    this.country_phone_code ?: 0
)

fun List<CountryDTO>.fromListCountryDTOToListCountryModel(): List<CountryModel> {
    return this.map { it.toCountryModel() }
}

fun CountryEntity.toCountryModel() = CountryModel(
    this.country_name,
    this.country_short_name,
    this.country_phone_code
)

fun List<CountryEntity>.fromListCountryEntityToListCountryModel(): List<CountryModel> {
    return this.map { it.toCountryModel() }
}

fun CountryDTO.toUserEntity() = CountryEntity(
    null,
    this.country_name ?: "",
    this.country_short_name ?: "",
    this.country_phone_code ?: 0
)

fun List<CountryDTO>.fromListCountryDTOToListCountryEntity(): List<CountryEntity> {
    return this.map { it.toUserEntity() }
}

