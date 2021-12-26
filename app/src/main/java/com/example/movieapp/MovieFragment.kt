package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.movieapp.adapter.MoviesAdapter
import com.example.movieapp.data.Movies
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.FragmentMovie2Binding
import com.example.movieapp.viewmodel.MoviesViewModel




class MovieFragment() : Fragment() {
    private var param1: String? = null
    lateinit var binding : FragmentMovie2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("type")

        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var adapter = MoviesAdapter(MoviesActivity.listTopRatedMovies,requireActivity())
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie2, container, false)
        binding.adpt = adapter
        val view = binding.root
        return  view
    }




}