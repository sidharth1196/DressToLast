package com.hackathon.dresstolast.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val priceRange: String,
    val reviews: Int,
    val durabilityIndex: Double,
    val commonIssues: ArrayList<String>? = null,
    var imageRes: Int? = null
)
