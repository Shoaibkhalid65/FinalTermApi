package com.example.finaltermapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName(value = "id") val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

@Serializable
data class Rating(
    val rate: Double,
    val count: Int
)

@Serializable
data class User(
    val id: Int,
    @SerialName("username") val userName: String,
    val email: String,
    val password: String
)