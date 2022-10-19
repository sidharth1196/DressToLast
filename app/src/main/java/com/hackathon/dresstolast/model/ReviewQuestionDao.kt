package com.hackathon.dresstolast.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewQuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReviewQuestion(reviewQuestions: ReviewQuestion)

    @Query("SELECT * FROM ReviewQuestion")
    fun getAllQuestions(): List<ReviewQuestion>

}