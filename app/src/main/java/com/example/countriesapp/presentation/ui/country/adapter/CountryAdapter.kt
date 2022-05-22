package com.example.countriesapp.presentation.ui.country.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ItemCountryBinding
import com.example.countriesapp.domain.model.CountryModel

class CountryAdapter(
    private val widthCard: Int = CoordinatorLayout.LayoutParams.MATCH_PARENT,
    private val onItemClick: (CountryModel) -> Unit
) : ListAdapter<CountryModel, CountryAdapter.RecentCountryViewHolder>(CountryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentCountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return RecentCountryViewHolder(view, widthCard, onItemClick)
    }

    override fun onBindViewHolder(holder: RecentCountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecentCountryViewHolder(
        view: View,
        private val widthCard: Int,
        private val onClickProduct: (CountryModel) -> Unit

    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCountryBinding.bind(view)
        private var currentCountry: CountryModel? = null

        init {
            view.setOnClickListener {
                currentCountry?.let(onClickProduct)
            }
        }

        fun bind(country: CountryModel) {
            currentCountry = country
            binding.root.layoutParams = binding.root.layoutParams.apply {
                width = widthCard
            }
            binding.tvName.text = country.country_name
            binding.tvShortName.text = country.country_short_name
            binding.tvPhoneCode.text = country.country_phone_code.toString()
        }
    }
}

object CountryDiffCallback : DiffUtil.ItemCallback<CountryModel>() {
    override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return oldItem.country_name == newItem.country_name
    }

    override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return oldItem == newItem
    }
}
