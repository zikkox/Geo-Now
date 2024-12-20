package com.example.geonow.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geonow.data.Country
import com.example.geonow.data.CountryDIffCallback
import com.example.geonow.databinding.ItemCountryBinding

class CountryAdapter(private val onClick: (Country) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var countryList = listOf<Country>()

    class CountryViewHolder(
        private val binding: ItemCountryBinding, private val onBookClick: (Country) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(country: Country) {
            Glide.with(binding.flagImageView.context).load(country.flags.png)
                .into(binding.flagImageView)

            binding.countryTextView.text = country.name.common
            binding.continentTextView.text = country.continents[0]

            binding.root.setOnClickListener {
                onBookClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountryViewHolder).bind(countryList[position])
    }

    override fun getItemCount() = countryList.size

    fun updateCountries(newList: List<Country>) {
        val diffCallback = CountryDIffCallback(countryList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        countryList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
