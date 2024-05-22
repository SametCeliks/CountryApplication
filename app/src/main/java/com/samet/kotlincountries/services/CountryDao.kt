package com.samet.kotlincountries.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.samet.kotlincountries.models.Country

@Dao
interface CountryDao {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>

    //Insert -> Insert Into
    //suspend -> coroutine, pause,resume
    //vararg -> multiple country object
    //List<Long> -> primary key

    @Query("SELECT * FROM country")
    suspend fun getAllCountries():List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}