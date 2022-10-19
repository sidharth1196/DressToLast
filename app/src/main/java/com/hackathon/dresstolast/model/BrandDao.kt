package com.hackathon.dresstolast.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBrand(brand: Brand)

    @Query("SELECT * FROM Brand WHERE name =:name")
    fun getBrandByName(name: String): Brand?

    @Query("UPDATE BRAND SET durabilityIndex =:index, reviews =:reviews WHERE id =:id")
    fun updateBrandDetailsByID(reviews: Int, index: Double, id: Int)

    @Query("SELECT * FROM BRAND")
    fun getAllBrands(): List<Brand>
}