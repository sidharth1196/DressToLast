package com.hackathon.dresstolast.model

data class ReviewQuestions(
    val id: Int = 0,
    val question: String,
    val imageUrl: Int,
    val options: List<String>,
    val isSingleAnswer: Boolean
)
