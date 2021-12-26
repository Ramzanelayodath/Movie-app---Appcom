package com.example.movieapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.adapter.CategoryAdapter
import com.example.movieapp.data.MovieCategories
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.viewmodel.CategoryViewModel

class MainActivity : AppCompatActivity() {
    lateinit var categoryViewModel : CategoryViewModel
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        categoryViewModel.getCategories()
        val dialog = ProgressDialog(this)
        dialog.setMessage("Loading...")
        dialog.show()
        categoryViewModel.categoryList.observe(this,
            Observer<ArrayList<MovieCategories>?> { categoryModel ->
                dialog.dismiss()
                var adapter = CategoryAdapter(categoryModel,this)
                binding.myAdapter = adapter
                })

        categoryViewModel.errorMessage.observe(this,Observer{
            dialog.dismiss()
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }
}