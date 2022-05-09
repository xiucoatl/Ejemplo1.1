package com.example.ejemplo11

import android.content.DialogInterface
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter (val articulos: List<Resultado>, private var setOnClickListener: (Resultado) -> Unit
): RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainViewHolder(layoutInflater.inflate(R.layout.item_articulo, parent,false ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val articulo : Resultado = articulos.get(position)
        holder.render(articulo)
        holder.view.setOnClickListener {
            setOnClickListener(articulo)
        }

    }

    override fun getItemCount(): Int {
        return articulos.size

    }
}

class MainViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun render(articulo: Resultado){
        view.findViewById<TextView>(R.id.title).text = articulo.title
        view.findViewById<TextView>(R.id.date).text = articulo.published_date
        Picasso.get().load(articulo.media[0].mediaMetadata[0].urlMetadata).into(view.findViewById<ImageView>(R.id.ivLogo))
    }
}
