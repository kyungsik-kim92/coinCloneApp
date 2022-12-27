package com.application.coincloneapp.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.application.coincloneapp.db.entity.InterestedCoinEntity
import com.application.coincloneapp.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()
    lateinit var selectedCoinList : LiveData<List<InterestedCoinEntity>>


    fun getAllInterestCoinData() = viewModelScope.launch {
        val coinList = dbRepository.getAllInterestCoinData().asLiveData()

        selectedCoinList = coinList
    }


    fun updateInterestCoinData(interestedCoinEntity: InterestedCoinEntity) = viewModelScope.launch(Dispatchers.IO) {

        if (interestedCoinEntity.selected){
            interestedCoinEntity.selected = false
        }else{
            interestedCoinEntity.selected = true
        }

        dbRepository.updateInterestCoinData(interestedCoinEntity)

    }

}