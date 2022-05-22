package com.example.countriesapp.presentation.ui.country

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentCountryBinding
import com.example.countriesapp.databinding.FragmentCountryMapBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class CountryMapFragment : Fragment() {

    private val args: CountryMapFragmentArgs by navArgs()
    private lateinit var binding: FragmentCountryMapBinding

    private val callback = OnMapReadyCallback { googleMap ->
        searchLocation(googleMap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun searchLocation(googleMap: GoogleMap) {
        val location: String = args.countryName
        var addressList: List<Address>? = null

        if (location == null || location == "") {
            Toast.makeText(requireContext(),getString(R.string.location_empty),Toast.LENGTH_SHORT).show()
        }
        else{
            val geoCoder = Geocoder(requireContext())
            try {
                addressList = geoCoder.getFromLocationName(location, 1)

            } catch (e: IOException) {
                e.printStackTrace()
            }
            val address = addressList!![0]
            val latLng = LatLng(address.latitude, address.longitude)
            googleMap!!.addMarker(MarkerOptions().position(latLng).title(location))
            googleMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }
}