package com.example.movieapp.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.AddMovieActivity
import com.example.movieapp.data.CategoryData
import com.example.movieapp.data.MovieCategories
import com.example.movieapp.repository.Repository
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class CategoryViewModel () : ViewModel() {

    val categoryList = MutableLiveData<ArrayList<MovieCategories>>()
    val errorMessage = MutableLiveData<String>()
    val repository = Repository()

    fun getCategories(){
        val response = repository.getCategories()
        response.enqueue(object :retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                try{
                    val category = Gson().fromJson(response.body(), CategoryData::class.java)
                    categoryList.value = category.movieCategories
                }catch (e:Exception){
                    errorMessage.postValue(e.message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }


}