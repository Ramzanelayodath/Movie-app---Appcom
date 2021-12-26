package com.example.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ImageViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.MovieImageClass
import com.example.movieapp.databinding.AdapterAddMovieBinding
import java.io.File


class ImageAdapter(private val data :ArrayList<MovieImageClass>, val ctx: Context)  :androidx.recyclerview.widget.RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = AdapterAddMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.binding.txtRemove.setOnClickListener {
            data.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    inner class ViewHolder(val binding: AdapterAddMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieImageClass){
            binding.model = item

        }
    }



    companion object{
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, file: File) { // This methods should not have any return type, = declaration would make it return that object declaration.
            Glide.with(view.context).load(file).into(view)
        }

    }


}