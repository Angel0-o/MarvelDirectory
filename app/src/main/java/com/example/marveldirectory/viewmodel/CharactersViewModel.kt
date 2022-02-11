package com.example.marveldirectory.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldirectory.model.repositories.CharactersRepository
import com.example.marveldirectory.model.data.MarvelResponse
import kotlinx.coroutines.launch

class CharactersViewModel:ViewModel() {
    val charactersLiveData = MutableLiveData<MarvelResponse>()

    fun getCharacters(ts:Int,limit:Int,offset:Int){
        viewModelScope.launch {
            val repository = CharactersRepository()
            val currentCharacters = repository.getCharacters(ts, limit, offset)
            charactersLiveData.postValue(currentCharacters)
        }
    }
}