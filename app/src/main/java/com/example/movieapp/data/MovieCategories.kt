package com.example.movieapp.data

import com.google.gson.annotations.SerializedName


data class MovieCategories (

  @SerializedName("category_id"    ) var categoryId    : Int?    = null,
  @SerializedName("category_name"  ) var categoryName  : String? = null,
  @SerializedName("category_image" ) var categoryImage : String? = null

)