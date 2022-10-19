package com.hackathon.dresstolast.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.dresstolast.model.Brand
import com.hackathon.dresstolast.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlertDialogViewModel(private val dataRepository: DataRepository): ViewModel() {
    val brand = MutableLiveData<Brand?>()

    fun getBrandByID(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
             brand.postValue(dataRepository.getBrandByName(name))
        }
    }
}