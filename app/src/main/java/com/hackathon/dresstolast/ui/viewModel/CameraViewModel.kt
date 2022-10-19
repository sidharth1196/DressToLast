package com.hackathon.dresstolast.ui.viewModel

import android.graphics.Bitmap
import androidx.camera.core.ImageProxy
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel: ViewModel() {
    val imageProxy = MutableLiveData<ImageProxy>()
    val imageBitmap = MutableLiveData<Bitmap>()
}