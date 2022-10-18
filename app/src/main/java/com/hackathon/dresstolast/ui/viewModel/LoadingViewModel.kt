package com.hackathon.dresstolast.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.dresstolast.model.Answers
import com.hackathon.dresstolast.model.Brand
import com.hackathon.dresstolast.model.ReviewQuestion
import com.hackathon.dresstolast.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoadingViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun insertReviewQuestion(reviewQuestion: ReviewQuestion) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.insertReviewQuestion(reviewQuestion)
        }
    }

    fun insertBrands(brand: Brand) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.insertBrand(brand)
        }
    }

    val RQList = listOf(
            ReviewQuestion(
                1,
                "How old is the garment?",
                "q1",
                listOf(
                    Answers("More than 3 years", 3),
                    Answers("Between 1-3 years", 2),
                    Answers("Less than a year", 1),
                    Answers("I don't know, the garment is second hand/vintage", 2)
                ),
                true
            ),
            ReviewQuestion(
                2,
                "How often do you wear the garment?",
                "q2",
                listOf(
                    Answers("More than once a week", 3),
                    Answers("Between 1-4 times per month", 2),
                    Answers("Less than once a month", 1),
                ),
                true
            ),
            ReviewQuestion(
                3,
                "How would you describe the garments condition?",
                "q3",
                listOf(
                    Answers("Durable - worn and well maintained", 5),
                    Answers("Acceptable - shows defects", 3),
                    Answers("Bad - has extensive defects", 1),
                ),
                true
            ),
            ReviewQuestion(
                4,
                "What's wrong with the garment?",
                "q4",
                listOf(
                    Answers("Buttons", 0),
                    Answers("Zipper", 0),
                    Answers("Seams", 0),
                    Answers("Fabric holes", 0),
                    Answers("Fabric changes", 0),
                    Answers("Color changes", 0),
                    Answers("Shrinking", 0),
                ),
                false
            )
        )

    val BrandList = listOf(
        Brand(
            1,
            "Weekday",
            "$$",
            468,
            10.0
        ),
        Brand(
            2,
            "Patagonia",
            "$$",
            392,
            9.5
        ),
        Brand(
            3,
            "Levi’s",
            "$$",
            314,
            9.1
        ),
        Brand(
            4,
            "H&M",
            "$",
            529,
            9.2
        ),
        Brand(
            5,
            "The North Face",
            "$$$",
            356,
            10.2
        ),
        Brand(
            6,
            "BEEN London",
            "$$$",
            187,
            10.1
        ),
        Brand(
            7,
            "Adidas",
            "$$",
            702,
            7.0
        ),
        Brand(
            8,
            "Zara",
            "$$",
            375,
            4.3
        ),

    )
}