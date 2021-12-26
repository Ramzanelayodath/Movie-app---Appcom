 package com.example.movieapp.data

import com.google.gson.annotations.SerializedName


data class AddMovieSuccessData (

    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null

)
