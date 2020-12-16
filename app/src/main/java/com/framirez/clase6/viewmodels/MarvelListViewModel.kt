package com.framirez.clase6.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.framirez.clase6.network.RetrofitProvider
import com.framirez.clase6.network.models.Character
import com.framirez.clase6.network.models.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelListViewModel : ViewModel() {
    private val retrofitProvider = RetrofitProvider()
    private val apiKey : String = "22a58f4b53610f4888a55d28bb44b519"
    private val hash : String = "064803ab13a043eaf43472a50b995d97"
    private val ts : String = "1"

    private val characterListResponse : MutableLiveData<List<Character>> = MutableLiveData()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getCharacterListResponse() : LiveData<List<Character>> {
        return characterListResponse
    }

    fun getIsLoading(): LiveData<Boolean> {
        return isMakingRequest
    }

    fun getIsError(): LiveData<Boolean> {
        return isError
    }

    fun getCharacterList() {
        isMakingRequest.postValue(true)
        retrofitProvider.getMarvelService().getCharacterList(ts, apiKey, hash).enqueue(object :
            Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                isMakingRequest.postValue(false)
                if (response.isSuccessful){
                    response.body()?.let{unwrappedresponse ->
                        characterListResponse.postValue(unwrappedresponse.data.results)
                    }
                }else {
                    isMakingRequest.postValue(false)
                    isError.postValue(true)
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                isMakingRequest.postValue(false)
                isError.postValue(true)
            }
        })
    }
}