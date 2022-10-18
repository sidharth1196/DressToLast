package com.hackathon.dresstolast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.BrandListItemBinding
import com.hackathon.dresstolast.model.Brand

class BrandAdapter(): RecyclerView.Adapter<BrandAdapter.BrandHolder>() {
    private var brandsList : List<Brand> = listOf()
    inner class BrandHolder(private val binding: BrandListItemBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var brand: Brand
        fun bindToView(brandItem: Brand){
            brand = brandItem

            binding.tvBrandName.text = brand.name
            binding.tvPriceRange.text = brand.priceRange
            binding.tvReviews.text = "${brand.reviews} reviews"
            binding.tvDurability.text = when {
                brand.durabilityIndex < 5.1 -> "Fragile"
                brand.durabilityIndex < 8.1 -> "Reliable"
                else -> "Durable"
            }
            brand.imageRes?.let { binding.ivDurability.setImageResource(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandHolder {
        val binding : BrandListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.brand_list_item, parent, false)
        return BrandHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandHolder, position: Int) {
        val brandItem = brandsList[position]
        holder.bindToView(brandItem)
    }

    override fun getItemCount(): Int {
        return brandsList.size
    }

    fun setBrandsList(list: List<Brand>) {
        brandsList = list
        notifyDataSetChanged()
    }
}