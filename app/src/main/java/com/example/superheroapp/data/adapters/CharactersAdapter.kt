package com.example.superheroapp.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroapp.model.Character
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.superheroapp.R
import com.example.superheroapp.databinding.ItemCharacterBinding
import com.example.superheroapp.utils.OnItemClickListener

class CharactersAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<CharactersAdapter.CharactersHolder>() {

    var characters = mutableListOf<Character>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCharactersList(characters: List<Character>) {
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharactersHolder, position: Int) {
        val item: Character = characters[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = characters.size

    class CharactersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCharacterBinding.bind(itemView)

        fun bind(item: Character, listener: OnItemClickListener?) {
            binding.cardCharacter.setOnClickListener { listener?.onItemClick(item) }

            binding.txtName.text = item.name
            binding.txtDate.text = item.modified

            val options: RequestOptions = RequestOptions().centerCrop().placeholder(R.drawable.default_placeholder).error(R.drawable.error_placeholder)
            val url = item.thumbnail.path + "." + item.thumbnail.extension

            Glide.with(itemView.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .into(binding.imgThumbnail)
        }
    }

}