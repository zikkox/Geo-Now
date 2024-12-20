package com.example.geonow.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.bundle.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.geonow.data.Country
import com.example.geonow.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: CountryViewModel by viewModel()

    private val adapter by lazy {
        CountryAdapter { clickedCountry ->
            onCountryClicked(clickedCountry)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setCollectors()
        viewModel.getCountries()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    private fun setCollectors() = lifecycleScope.launch {
        viewModel.countryFlow.collect { response ->
            when (response) {
                is Response.Loading -> {
                    // Handle loading
                }
                is Response.Success -> {
                    response.data?.let {
                        adapter.updateCountries(it)
                        getCountriesSize()
                    }
                }
                is Response.Error -> {
                    // Handle error
                }
            }
        }
    }

    private fun onCountryClicked(country: Country) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(country)
        findNavController().navigate(action)
    }

    private fun getCountriesSize() {
        val size = binding.recyclerView.adapter?.itemCount.toString()
        binding.countTextView.text = "($size)"
    }
}
