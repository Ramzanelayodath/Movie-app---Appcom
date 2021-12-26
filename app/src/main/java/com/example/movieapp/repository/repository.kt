package com.example.movieapp.repository

import com.example.movieapp.network.NetworkClient
import java.io.File

class Repository () {

    fun getCategories() = NetworkClient().getINSTANCE()!!.getCategories()

    fun getMovies(category_id : String) = NetworkClient().getINSTANCE()!!.getMovies(category_id)

    fun addMovies(category_id: String,movie_name:String,movie_rating:String,movie_description:String,movie_image: File) =
         NetworkClient().getINSTANCE()!!.addMovie(category_id,movie_name,movie_rating,movie_description,movie_image)
}