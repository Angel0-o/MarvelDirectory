package com.example.marveldirectory.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.marveldirectory.R
import com.example.marveldirectory.databinding.ItemHeroBinding
import com.example.marveldirectory.model.data.DataCharacter

class CharactersListAdapter(val marvelHeroes:List<DataCharacter> = emptyList(),
                            private val onClickListener:(DataCharacter) -> Unit): Adapter<CharactersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_hero,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHero(marvelHeroes.get(position),onClickListener)
    }

    override fun getItemCount(): Int = marvelHeroes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding:ItemHeroBinding

        fun bindHero(dataCharacter: DataCharacter,onClickListener:(DataCharacter) -> Unit){
            binding = ItemHeroBinding.bind(itemView)
            binding.itemHeroName.setText(dataCharacter.name)
            Glide.with(itemView.context).load("${dataCharacter.thumbnail.path}.${dataCharacter.thumbnail.extension}").centerCrop().into(binding.itemHeroImage)
            itemView.setOnClickListener{onClickListener(dataCharacter)}
        }
    }
}