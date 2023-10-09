package com.example.albumsapp.domain.model

data class User(
    val id: Int,
    val name: String,
    val address: Address?
)

data class Address(
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String
)