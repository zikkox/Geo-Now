package com.example.geonow.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.bundle.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.geonow.data.Country
import com.example.geonow.databinding.FragmentDetailsBinding
import com.example.geonow.databinding.FragmentHomeBinding
import com.example.geonow.domain.CountryRepository

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindCountry(args.country)
        clickListeners()
    }

    private fun bindCountry(country: Country){
        binding.officialTextView.text = country.name.official
        binding.nameTextView.text = country.name.common
        binding.continentTextView.text = country.continents.joinToString(", ")

        Glide.with(binding.flagImageView.context).load(country.flags.png)
            .into(binding.flagImageView)
    }

    private fun clickListeners(){
        binding.backButton.setOnClickListener{
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
    }
}