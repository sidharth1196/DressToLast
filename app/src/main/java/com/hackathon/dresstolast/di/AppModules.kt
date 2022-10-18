package com.hackathon.dresstolast.di

import android.app.Application
import androidx.room.Room
import com.hackathon.dresstolast.model.AppDatabase
import com.hackathon.dresstolast.model.BrandDao
import com.hackathon.dresstolast.model.ReviewQuestionDao
import com.hackathon.dresstolast.repository.DataRepository
import com.hackathon.dresstolast.ui.viewModel.CameraViewModel
import com.hackathon.dresstolast.ui.viewModel.LoadingViewModel
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    fun provideDB(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "dressToLastDB")
            .build()
    }
    fun provideReviewQuestionDao(db: AppDatabase): ReviewQuestionDao {
        return db.reviewQuestionDao()
    }
    fun provideBrandDao(db: AppDatabase): BrandDao {
        return db.brandDao()
    }

    single { provideDB(androidApplication()) }
    single { provideReviewQuestionDao(get()) }
    single { provideBrandDao(get()) }
}

val viewModelModule = module {
    viewModel { CameraViewModel() }
    viewModel { LoadingViewModel(get()) }
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { DataRepository(get(), get()) }
}