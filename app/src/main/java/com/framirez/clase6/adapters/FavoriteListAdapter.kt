package com.framirez.clase6.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.framirez.clase6.R
import com.framirez.clase6.db.CharacterEntity
import kotlinx.android.synthetic.main.favorite_list_cell.view.*

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>() {

    var chracters: List<CharacterEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bin(character: CharacterEntity) {
            itemView.tv_name_favorite.text = character.name
            itemView.tv_description_favorite.text = character.description
            Glide.with(itemView.context)
                    .load(character.picture)
                    .circleCrop()
                    .into(itemView.iv_picture_favorite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_list_cell, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount() = chracters.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bin(chracters[position])
    }

}