package com.dgtic.androidmodule1.ejercise.home.carlogarcia.homework.model

import java.io.Serializable

data class RegisterData (
    val user: String,
    val pass: String,
    val name: String,
    val mail: String,
    val phone: String,
    val age: Int,
    val gander: String
) : Serializable
