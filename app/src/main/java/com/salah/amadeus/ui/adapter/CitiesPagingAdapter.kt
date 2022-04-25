package com.salah.amadeus.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.salah.amadeus.data.local.entity.DataEntity
import com.salah.amadeus.databinding.AdapterCitiesItemBinding
import java.lang.String
import java.util.*


class CitiesPagingAdapter :
    PagingDataAdapter<DataEntity, CitiesPagingAdapter.CityViewHolder>(DIFF_CALLBACK) {
    class CityViewHolder(val binding: AdapterCitiesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType")
        fun bind(data: DataEntity?) {
            data?.let {
                binding.weatherChipGroup.removeAllViews()
                it.weather.forEach() {
                    val chip = Chip(binding.root.context)
                    chip.text = it.main
                    binding.weatherChipGroup.addView(chip)
                }
            }
        }

        fun openCoordinatesInMap(data: DataEntity?) {
            data?.let {
                val lat = data.city.coord.lat
                val long = data.city.coord.lon
                val uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, long)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                binding.root.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataEntity>() {

            override fun areItemsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterCitiesItemBinding.inflate(layoutInflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        getItem(position)?.let {
            val data = getItem(position)
            holder.binding.data = data
            holder.bind(data)
            holder.binding.mapButton.setOnClickListener {
                holder.openCoordinatesInMap(data)
            }
        }
    }
}
