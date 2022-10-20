package com.hackathon.dresstolast.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var priceRange: String = "",
    var reviews: Int = 0,
    var durabilityIndex: Double = 0.0,
    var commonIssues: ArrayList<String>? = null,
    var imageRes: Int? = null,
    var docId: String = "",
    var brandLogo: Int? = null
)
