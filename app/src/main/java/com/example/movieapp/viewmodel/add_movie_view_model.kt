package com.example.movieapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.AddMovieSuccessData
import com.example.movieapp.data.AddMoviesData
import com.example.movieapp.data.CategoryData
import com.example.movieapp.data.MovieImageClass
import com.example.movieapp.repository.Repository
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import java.io.File

class AddMovieViewModel : ViewModel() {

    var movieTitle = MutableLiveData<String>()
    var movieDescription =MutableLiveData<String>()
    var rating = MutableLiveData<String>()
    var listImages = MutableLiveData<ArrayList<MovieImageClass>>()
    var movieLiveData = MutableLiveData<AddMoviesData>()
    var repository  = Repository()
    var movieAddedLiveData = MutableLiveData<AddMovieSuccessData>()
    var errorMessage = MutableLiveData<String>()

    fun getData() : MutableLiveData<AddMoviesData>{
        if(movieLiveData == null){
            movieLiveData = MutableLiveData()
        }

        return  movieLiveData

    }

    fun onClick(v:View){
        var movieData = AddMoviesData(movieTitle.value!!,movieDescription.value!!,rating.value!!,listImages.value!!)
        movieLiveData.value = movieData

    }

    fun addMovie(category_id: String,movie_name:String,movie_rating:String,movie_description:String,movie_image: File){
        val response = repository.addMovies(category_id,movie_name,movie_rating,movie_description,movie_image)
        response.enqueue(object :retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
               try {
                    movieAddedLiveData.value = Gson().fromJson(response.body(), AddMovieSuccessData::class.java)
               }catch (e:Exception){
                   errorMessage.value = e.toString()
               }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
               errorMessage.value = t.toString()
            }
        })
    }


}