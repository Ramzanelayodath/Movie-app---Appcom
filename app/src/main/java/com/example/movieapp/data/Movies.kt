package com.example.movieapp.data

import com.google.gson.annotations.SerializedName



data class Movies (

	@SerializedName("movie_id") val movie_id : Int,
	@SerializedName("category_id") val category_id : Int,
	@SerializedName("movie_name") val movie_name : String,
	@SerializedName("movie_rating") val movie_rating : Int,
	@SerializedName("movie_description") val movie_description : String,
	@SerializedName("movie_image") val movie_image : String
)