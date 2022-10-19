package com.hackathon.dresstolast.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.dresstolast.model.Brand
import com.hackathon.dresstolast.model.ReviewQuestion
import com.hackathon.dresstolast.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataRepository: DataRepository
): ViewModel() {
    val questions = MutableLiveData<List<ReviewQuestion>>()
    val brands = MutableLiveData<List<Brand>>()
    val answers: MutableMap<String, Int> = mutableMapOf()
    val brand: String = ""

    fun getAllQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            questions.postValue(dataRepository.getAllReviewQuestions())
        }
    }

    fun getAllBrands() {
        viewModelScope.launch(Dispatchers.IO) {
            brands.postValue(dataRepository.getAllBrands())
        }
    }

    fun calculateDurabilityIndex() {
        var sum = 0
        answers.values.forEach {
            sum += it
        }
        Log.d("DTL", "sum = $sum")
        // addToBrandDurabilityIndex(sum)
    }

    private fun addToBrandDurabilityIndex(sum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val brand = dataRepository.getBrandByName(brand)
            brand?.let {
                val index = (it.durabilityIndex * it.reviews + sum) / (it.reviews + 1)
                dataRepository.updateBrandDetailsById(it.reviews+1, index, it.id)
            }

        }
    }
}