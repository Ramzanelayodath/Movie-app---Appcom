package com.example.movieapp.viewmodel

import com.example.movieapp.data.Movies
import MoviesList
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.AddMovieActivity
import com.example.movieapp.data.MovieCategories
import com.example.movieapp.repository.Repository
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class MoviesViewModel () : ViewModel() {

    val moviesList = MutableLiveData<ArrayList<Movies>>()
    val topRatedMovieList = MutableLiveData<ArrayList<Movies>>()
    val lowRatedMovieList = MutableLiveData<ArrayList<Movies>>()
    val errorMessage = MutableLiveData<String>()
    val repository = Repository()

    fun getMovies(category_id : String){
        val response = repository.getMovies(category_id)
        response.enqueue(object :retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                try{
                    print(response.body())
                    val movies = Gson().fromJson(response.body(), MoviesList::class.java)
                    moviesList.postValue(movies.movies)
                }catch (e:Exception){
                    errorMessage.postValue(e.message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun sortMovies(moviesList:ArrayList<Movies>){

         val  topRatedMovies =  moviesList.filter { movies -> movies.movie_rating >= 3  } as ArrayList<Movies>
         val  lowRatedMovies =   moviesList.filter { movies -> movies.movie_rating < 3  } as ArrayList<Movies>

        topRatedMovieList.postValue(topRatedMovies)
        lowRatedMovieList.postValue(lowRatedMovies)
    }



}