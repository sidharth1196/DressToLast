package com.hackathon.dresstolast.di

import com.hackathon.dresstolast.ui.viewModel.CameraViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

}

val viewModelModule = module {
    viewModel { CameraViewModel() }
}