package com.framirez.clase6.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.framirez.clase6.R
import com.framirez.clase6.network.models.Character
import com.framirez.clase6.viewmodels.CreateCharacterViewModel
import kotlinx.android.synthetic.main.marvel_character_cell.view.*

class MarvelListAdapter(val clickListener: (Character) -> Unit) : RecyclerView.Adapter<MarvelListAdapter.MarvelCharacterViewHolder>() {

    var characterList: List<Character> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class MarvelCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.tv_name.text = character.name
            itemView.tv_description.text = character.description
            val formttedUrl = "${character.thumbnail.path}.${character.thumbnail.extension}".replace("http", "https")
            Glide.with(itemView.context)
                    .load(formttedUrl)
                    .circleCrop()
                    .into(itemView.iv_picture)
            itemView.setOnClickListener{
                clickListener.invoke(character)
            }

            itemView.tv_name.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.marvel_character_cell, parent, false)
        return MarvelCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount() = characterList.size

}