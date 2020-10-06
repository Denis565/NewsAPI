package com.example.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemAdapter (
    private val context: Context,
    private val images: List<ItemOfList>,
    val listener: (ItemOfList) ->Unit) : RecyclerView.Adapter<ItemAdapter.ImageViewHolder>(){

    class ImageViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageSrc = view.findViewById<ImageView>(R.id._image_item)
        val title = view.findViewById<TextView>(R.id._title)

        fun bindView(image: ItemOfList,listener: (ItemOfList) -> Unit){

            itemView.setOnClickListener { listener(image) }
            title.text = image.imageTitle

            Picasso
                .get()
                .load(image.photo)
                .fit()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageSrc)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.image_item,parent, false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position],listener)
    }
}




