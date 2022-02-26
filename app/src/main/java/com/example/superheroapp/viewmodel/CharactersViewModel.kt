package com.example.superheroapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.domain.GetAllSuperheros
import com.example.superheroapp.model.CharactersResponse
import com.example.superheroapp.model.Character
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel: ViewModel() {

    // The list does not change but the content of the elements of that list does.
    // Therefore a val is used but also a MutableLiveData.
    val superherosList = MutableLiveData<List<Character>>()
    val currentPage = MutableLiveData<Int>()
    val isInitializing = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    private val getAllSuperherosUseCase = GetAllSuperheros()

    fun onCreate(){
        currentPage.postValue(0)
    }

    fun getSuperheros(offset:Int) {
        viewModelScope.launch {
            setLoadingState(offset)

            val response = getAllSuperherosUseCase.getSuperheros(offset)

            response.enqueue(object : Callback<CharactersResponse?> {
                override fun onResponse(call: Call<CharactersResponse?>, response: Response<CharactersResponse?>) {
                    superherosList.postValue(response.body()?.data?.characters)
                    currentPage.postValue(currentPage.value!! + 1)
                    isInitializing.postValue(false)
                    isLoading.postValue(false)
                }

                override fun onFailure(call: Call<CharactersResponse?>, t: Throwable) {
                    superherosList.postValue(mutableListOf())
                }

            })
        }
    }

    private fun setLoadingState(offset:Int){
        if(offset == 0) {
            isInitializing.postValue(true)
            isLoading.postValue(false)
        }
        else {
            isInitializing.postValue(false)
            isLoading.postValue(true)
        }
    }

}