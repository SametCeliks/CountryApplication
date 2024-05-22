package com.samet.kotlincountries.services

import com.samet.kotlincountries.models.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //Get, Post
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //base url -> https://raw.githubusercontent.com/
    //Ext-> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Country>>

}