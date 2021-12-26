package com.example.movieapp.network
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkInterface {

    @GET(Urls.categories_url)
    fun getCategories() : Call<JsonObject>

    @GET(Urls.movies_url)
    fun getMovies(@Query("category_id")category_id : String) :Call<JsonObject>

    @Multipart
    @POST(Urls.add_movies_url)
    fun addMovie(@Part("category_id")category_id: RequestBody,
                 @Part("movie_name") movie_name : RequestBody,
                 @Part("movie_rating")movie_rating : RequestBody,
                 @Part("movie_description")movie_description : RequestBody,
                 @Part("movie_image")movie_image : RequestBody ) : Call<JsonObject>
}