package com.example.superheroapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentCharactersDetailedBinding
import com.example.superheroapp.model.Character
import com.example.superheroapp.utils.convertLongToTime

class CharactersDetailedFragment : Fragment() {

    private lateinit var binding: FragmentCharactersDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersDetailedBinding.inflate(layoutInflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val item: Character = arguments?.getSerializable("character") as Character
        val options: RequestOptions = RequestOptions().centerCrop().placeholder(R.drawable.default_placeholder).error(R.drawable.error_placeholder)
        val url = item.thumbnail.path + "." + item.thumbnail.extension

        context?.let {
            Glide.with(it)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .into(binding.imgCharacter)
        }

        binding.txtName.text = item.name
        binding.txtDescription.text = item.description
        binding.txtDate.text = item.modified.time.convertLongToTime()

        binding.fabBack.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_charactersDetailedFragment_to_charactersFragment)
        }
    }

}