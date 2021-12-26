package com.example.movieapp.adapter

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


class CategoryAdapter(private val data :ArrayList<MovieCategories>,val ctx:Context)  :androidx.recyclerview.widget.RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = AdapterCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
       return  data.size
    }

    inner class ViewHolder(val binding: AdapterCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MovieCategories){
            binding.model = item
            binding.img.setOnClickListener {
                val intent = Intent(ctx, MoviesActivity::class.java)
                intent.putExtra("id",item.categoryId.toString())
                intent.putExtra("categoryName",item.categoryName)
                ctx!!.startActivity(intent)
            }
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