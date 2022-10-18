package com.hackathon.dresstolast.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReviewQuestion(
    @PrimaryKey
    val id: Int = 0,
    val question: String,
    val imageUrl: String,
    val options: List<Answers>,
    val isSingleAnswer: Boolean,
    var imageRes: Int? = null
)

data class Answers(
    val label: String,
    val value: Int
)
