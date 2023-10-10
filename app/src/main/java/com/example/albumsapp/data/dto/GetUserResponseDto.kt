package com.example.albumsapp.data.dto

import com.example.albumsapp.domain.model.User
import com.example.albumsapp.domain.model.Address as DomainAddress

data class GetUserResponseDto(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun GetUserResponseDto.toUser() = User(
    id,
    name,
    address.let {
        DomainAddress(
            it.city,
            it.street,
            it.suite,
            it.zipcode
        )
    }
)

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)

data class Geo(
    val lat: String,
    val lng: String
)