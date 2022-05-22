package com.example.countriesapp.presentation.ui.country

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.countriesapp.common.PrefsHelper
import com.example.countriesapp.common.PrefsHelper.accessToken
import com.example.countriesapp.databinding.FragmentCountryBinding
import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.presentation.ui.country.adapter.CountryAdapter
import com.example.countriesapp.util.common.Errors
import com.example.countriesapp.common.error.ErrorMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CountryFragment : Fragment() {

    @Inject
    lateinit var errorMessage: ErrorMessage

    private val adapter: CountryAdapter = CountryAdapter(onItemClick = this::goToDetail)

    private val viewModel: CountryViewModel by viewModels()
    private lateinit var binding: FragmentCountryBinding
    private lateinit var prefs : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        prefs = PrefsHelper.customPreference(requireContext())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        initViewStateObserver()
        setupRecycler()
        loadCountries()
    }

    private fun setViewState(viewState: CountryViewState) {
        when (viewState) {
            is ShowError -> showError(viewState.error)
            ShowLoading -> showLoader()
            is ShowCountries -> showSuccessSearchUsers(viewState.data)
        }
    }

    private fun showSuccessSearchUsers(data: List<CountryModel>) {
        hideLoader()
        hideError()
        setDataInList(data)
    }

    private fun setDataInList(data: List<CountryModel>) {
        adapter.submitList(data)
    }

    private fun setupRecycler() {
        binding.rvCountriesList.adapter = adapter
    }

    private fun initViewStateObserver() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::setViewState))
    }

    private fun loadCountries() {
        val apiToken = prefs.accessToken.toString()
        hideError()
        viewModel.getCountries("Bearer "+apiToken)
    }

    private fun showLoader() {
        binding.pbProgress.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.pbProgress.visibility = View.GONE
    }

    private fun showError(error: Errors) {
        hideLoader()
        val message = errorMessage.getMessage(error)
        binding.vError.root.visibility = View.VISIBLE
        binding.vError.tvErrorMessage.text = message
        binding.vError.btnErrorRetry.setOnClickListener {
            loadCountries()
        }
    }

    private fun hideError() {
        binding.vError.root.visibility = View.GONE
    }


    private fun goToDetail(country: CountryModel) {
        val action = CountryFragmentDirections.actionCountryFragmentToCountryDetailFragment(country.country_name)
        findNavController().navigate(action)
    }

}