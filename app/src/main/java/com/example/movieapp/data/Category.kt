package com.example.movieapp.data

import com.google.gson.annotations.SerializedName


data class CategoryData (

  @SerializedName("success"          ) var success         : Boolean?                   = null,
  @SerializedName("movie_categories" ) var movieCategories : ArrayList<MovieCategories> = arrayListOf()

)