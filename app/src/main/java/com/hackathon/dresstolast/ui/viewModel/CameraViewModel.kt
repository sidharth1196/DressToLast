package com.hackathon.dresstolast.ui.viewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.camera.core.ImageProxy
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.text.Text
import com.hackathon.dresstolast.model.Brand
import com.hackathon.dresstolast.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CameraViewModel(
    private val dataRepository: DataRepository
): ViewModel() {
    val imageProxy = MutableLiveData<ImageProxy>()
    val imageBitmap = MutableLiveData<Bitmap>()
    val brand = MutableLiveData<Brand?>()

    fun processImageToGetBrand(textBlocks: MutableList<Text.TextBlock>) {
        viewModelScope.launch(Dispatchers.IO) {
            val brands = dataRepository.getAllBrands()
            var filtered: MutableList<Brand> = mutableListOf()
            for (block in textBlocks) {
                for (line in block.lines) {
                    for (element in line.elements) {
                        filtered.addAll(brands.filter {
                            it.name.equals(element.text, true)
                        })
                        brand.postValue(filtered.firstOrNull())
                    }
                }
            }
            Log.d("DTL", "Filtered: $filtered")
        }
    }
}