package com.example.movieapp.network


import com.google.gson.JsonObject
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import java.io.File
import okhttp3.MultipartBody

import okhttp3.RequestBody





class NetworkClient{
    lateinit var networkInterface: NetworkInterface
    private var INSTANCE: NetworkClient? = null

    constructor(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Urls.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkInterface = retrofit.create(NetworkInterface::class.java)
    }

    fun getINSTANCE(): NetworkClient? {
        if (null == INSTANCE) {
            INSTANCE = com.example.movieapp.network.NetworkClient()
        }
        return INSTANCE
    }

    fun getCategories() : Call<JsonObject>{
         return  networkInterface.getCategories()
    }

    fun getMovies(category_id : String) : Call<JsonObject>{
        return  networkInterface.getMovies(category_id)
    }

    fun addMovie(category_id: String,movie_name:String,movie_rating:String,movie_description:String,movie_image:File) :Call<JsonObject>{
        val file: RequestBody = RequestBody.create(
            MediaType.parse("image/*"),
            movie_image
        )
        val category_id: RequestBody = RequestBody.create(
            MediaType.parse("text/plain"),
            category_id
        )

        val movie_name: RequestBody = RequestBody.create(
            MediaType.parse("text/plain"),
            movie_name
        )
        val movie_rating: RequestBody = RequestBody.create(
            MediaType.parse("text/plain"),
            movie_rating
        )
        val movie_description: RequestBody = RequestBody.create(
            MediaType.parse("text/plain"),
            movie_description
        )


        return  networkInterface.addMovie(category_id,movie_name,movie_rating,movie_description,file)
    }

}