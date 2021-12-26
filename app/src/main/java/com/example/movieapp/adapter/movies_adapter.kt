package com.example.movieapp.adapter

import com.example.movieapp.data.Movies
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.MoviesActivity
import com.example.movieapp.data.MovieCategories
import com.example.movieapp.databinding.AdapterCategoryBinding
import com.example.movieapp.databinding.AdapterMovieBinding


class MoviesAdapter(private val data :ArrayList<Movies>, val ctx: Context)  :androidx.recyclerview.widget.RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = AdapterMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    inner class ViewHolder(val binding: AdapterMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Movies){
            binding.model = item

        }
    }



    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) { // This methods should not have any return type, = declaration would make it return that object declaration.
            Glide.with(view.context).load(url).into(view)
        }
    }
}