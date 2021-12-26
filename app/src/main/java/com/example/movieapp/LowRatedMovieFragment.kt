package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.movieapp.adapter.MoviesAdapter
import com.example.movieapp.databinding.FragmentLowRatedMovieBinding
import com.example.movieapp.databinding.FragmentMovie2Binding


class LowRatedMovieFragment : Fragment() {

    lateinit var binding : FragmentLowRatedMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var adapter = MoviesAdapter(MoviesActivity.listLowRatedMovies,requireActivity())
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_low_rated_movie, container, false)
        binding.adpt = adapter
        val view = binding.root
        return  view
    }


}