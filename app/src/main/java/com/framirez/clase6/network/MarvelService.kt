package com.framirez.clase6.network

import com.framirez.clase6.network.models.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    fun getCharacterList( @Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String) : Call<CharacterResponse>

}