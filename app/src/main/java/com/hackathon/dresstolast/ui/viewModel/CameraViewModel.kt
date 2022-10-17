package com.hackathon.dresstolast.ui.viewModel

import androidx.camera.core.ImageProxy
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel: ViewModel() {
    val imageProxy = MutableLiveData<ImageProxy>()
}