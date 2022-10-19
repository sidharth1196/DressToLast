package com.hackathon.dresstolast.repository

import com.hackathon.dresstolast.model.Brand
import com.hackathon.dresstolast.model.BrandDao
import com.hackathon.dresstolast.model.ReviewQuestion
import com.hackathon.dresstolast.model.ReviewQuestionDao

class DataRepository(
    private val reviewQuestionDao : ReviewQuestionDao,
    private val brandDao: BrandDao
) {
    fun insertReviewQuestion(reviewQuestion: ReviewQuestion) {
        reviewQuestionDao.addReviewQuestion(reviewQuestion)
    }

    fun getAllReviewQuestions(): List<ReviewQuestion> = reviewQuestionDao.getAllQuestions()

    fun getBrandByName(name: String) = brandDao.getBrandByName(name)

    fun updateBrandDetailsById(reviews: Int, index: Double, id: Int) = brandDao.updateBrandDetailsByID(reviews, index, id)

    fun insertBrand(brand: Brand) = brandDao.insertBrand(brand)

    fun getAllBrands() = brandDao.getAllBrands()
}