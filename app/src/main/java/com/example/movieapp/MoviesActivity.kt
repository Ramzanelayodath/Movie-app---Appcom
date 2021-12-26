package com.example.movieapp

import com.example.movieapp.data.Movies
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.movieapp.adapter.ViewPagerAdapter
import com.example.movieapp.databinding.ActivityMoviesBinding
import com.example.movieapp.viewmodel.MoviesViewModel


class MoviesActivity : AppCompatActivity() {
    lateinit var binding : ActivityMoviesBinding
    lateinit var viewModel : MoviesViewModel
    var listMovies : ArrayList<Movies> = ArrayList()
    companion object{
        var listTopRatedMovies : ArrayList<Movies> = ArrayList()
        var listLowRatedMovies : ArrayList<Movies> = ArrayList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.getMovies(intent.getStringExtra("id")!!)

        title = intent.getStringExtra("categoryName")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        val dialog = ProgressDialog(this)
        dialog.setMessage("Loading...")
        dialog.show()

        viewModel.moviesList.observe(this,{
            it ->
             dialog.dismiss()
             listMovies = it

            viewModel.sortMovies(listMovies)

        })

        viewModel.errorMessage.observe(this,{
            it ->
             Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

        viewModel.sortMovies(listMovies)
        viewModel.topRatedMovieList.observe(this,{
            listTopRatedMovies = it

            viewModel.lowRatedMovieList.observe(this,{
                listLowRatedMovies = it
                binding.tabs!!.setupWithViewPager(binding.viewPager)
                setupViewPager()

            })


        })





    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupViewPager() {
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MovieFragment().apply {
            arguments = Bundle().apply {
                putSerializable("type","top")


            }
        }, "Top Rated")
        adapter.addFragment(LowRatedMovieFragment().apply {
            arguments = Bundle().apply {
                putSerializable("type","low")
            }
        }, "Low Rated")
        binding.viewpager  = adapter
    }

    fun intentToAddMovieScreen(v:View){
        startActivity(Intent(this,AddMovieActivity::class.java)
            .putExtra("id",intent.getStringExtra("id"))
            .putExtra("categoryName",intent.getStringExtra("categoryName")))
    }


}