package com.example.movieapp

import android.app.Activity
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.ImageAdapter
import com.example.movieapp.data.MovieImageClass
import com.example.movieapp.databinding.ActivityAddMovieBinding
import com.example.movieapp.viewmodel.AddMovieViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class AddMovieActivity : AppCompatActivity() {
    lateinit var addMovieViewModel : AddMovieViewModel
    var addMovieActivitiyBinding : ActivityAddMovieBinding? = null
    var listImages = ArrayList<MovieImageClass>()
    var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        addMovieActivitiyBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_movie)
        addMovieViewModel = ViewModelProviders.of(this).get(AddMovieViewModel::class.java)
        addMovieActivitiyBinding!!.lifecycleOwner = this
        addMovieActivitiyBinding!!.model = addMovieViewModel
        
        addMovieActivitiyBinding!!.txtCatName.text = intent.getStringExtra("categoryName")

        title = "Add Movie"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)



        var adapter = ImageAdapter(listImages,this)
        addMovieActivitiyBinding!!.recyclerPhotos.adapter = adapter
        addMovieActivitiyBinding!!.recyclerPhotos.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        addMovieViewModel.getData().observe(this,{
            if (TextUtils.isEmpty(Objects.requireNonNull(it).name)) {
                addMovieActivitiyBinding!!.edtMovieName.error = "Enter Movie Name"
                addMovieActivitiyBinding!!.edtMovieName.requestFocus()
            }else if (TextUtils.isEmpty(Objects.requireNonNull(it).description)) {
                addMovieActivitiyBinding!!.edtMovieDesc.error = "Enter Movie Description"
                addMovieActivitiyBinding!!.edtMovieDesc.requestFocus()
            }else if (TextUtils.isEmpty(Objects.requireNonNull(it).rating)) {
                addMovieActivitiyBinding!!.edtRating.error = "Select Rating"
                addMovieActivitiyBinding!!.edtRating.requestFocus()
            }else  if(it.imagesList.isEmpty()){
                Toast.makeText(this,"Select Image",Toast.LENGTH_SHORT).show()
            }else{
                addMovieViewModel.addMovie(intent.getStringExtra("id")!!,it.name,it.rating,it.description,it.imagesList[0].image)
                progressDialog!!.setCancelable(false)
                progressDialog!!.setMessage("Please Wait....")
                progressDialog!!.show()
            }
        })

        addMovieViewModel.movieAddedLiveData.observe(this,{
            progressDialog!!.dismiss()
            addMovieActivitiyBinding!!.edtRating.text.clear()
            addMovieActivitiyBinding!!.edtMovieDesc.text.clear()
            addMovieActivitiyBinding!!.edtMovieName.text.clear()
            listImages.clear()
            addMovieViewModel.listImages.value!!.clear()
            addMovieActivitiyBinding!!.recyclerPhotos.adapter!!.notifyDataSetChanged()
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
        })

        addMovieViewModel.errorMessage.observe(this,{
            progressDialog!!.dismiss()
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

    }


      fun showRatingDialog(v: View){
        val builderSingle: AlertDialog.Builder = AlertDialog.Builder(this)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        arrayAdapter.add("1")
        arrayAdapter.add("2")
        arrayAdapter.add("3")
        arrayAdapter.add("4")
        arrayAdapter.add("5")

        builderSingle.setAdapter(arrayAdapter
        ) { p0, p1 ->
            addMovieActivitiyBinding!!.edtRating.setText(arrayAdapter.getItem(p1))
            addMovieViewModel.rating.postValue(arrayAdapter.getItem(p1))
            p0.dismiss() }

        builderSingle.show()
    }

    fun openImagePicker(v:View){
        TedImagePicker.with(this)
            .start { uri ->
                listImages.add(MovieImageClass(File(com.example.movieapp.utils.FileUtils.getPath(uri))))
                addMovieActivitiyBinding!!.recyclerPhotos.adapter!!.notifyDataSetChanged()
                addMovieViewModel.listImages.postValue(listImages)
            }
    }

    fun cancel(v:View){
        onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}