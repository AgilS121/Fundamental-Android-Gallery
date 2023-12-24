package com.example.daftarlukisantermahal

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ListGalleryAdapter(private val listGallery: ArrayList<Gallery>) : RecyclerView.Adapter<ListGalleryAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_gallery, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listGallery[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listGallery[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listGallery.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.item_description)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Gallery)
    }
}