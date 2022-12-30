package com.application.coincloneapp.view.main

import androidx.lifecycle.*
import com.application.coincloneapp.datamodel.UpDownDataSet
import com.application.coincloneapp.db.entity.InterestedCoinEntity
import com.application.coincloneapp.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()
    lateinit var selectedCoinList: LiveData<List<InterestedCoinEntity>>


    private val _arr15min = MutableLiveData<List<UpDownDataSet>>()
    val arr15min : LiveData<List<UpDownDataSet>>
            get() = _arr15min

    private val _arr30min = MutableLiveData<List<UpDownDataSet>>()
    val arr30min : LiveData<List<UpDownDataSet>>
        get() = _arr30min

    private val _arr45min = MutableLiveData<List<UpDownDataSet>>()
    val arr45min : LiveData<List<UpDownDataSet>>
        get() = _arr45min





    fun getAllInterestCoinData() = viewModelScope.launch {
        val coinList = dbRepository.getAllInterestCoinData().asLiveData()

        selectedCoinList = coinList
    }


    fun updateInterestCoinData(interestedCoinEntity: InterestedCoinEntity) =
        viewModelScope.launch(Dispatchers.IO) {

            if (interestedCoinEntity.selected) {
                interestedCoinEntity.selected = false
            } else {
                interestedCoinEntity.selected = true
            }

            dbRepository.updateInterestCoinData(interestedCoinEntity)

        }

    // PriceChangeFragment
    // 1. 관심있다고 선택한 코인 리스트를 가져와서
    // 2. 반복문을 통해 하나씩 가져와서
    // 3. 저장된 코인 가격 리스트를 가져와서
    // 4. 시간대마다 어떻게 변경되었는지를 알려주는 로직

    fun getAllSelectedCoinData() = viewModelScope.launch(Dispatchers.IO) {

        // 1.
        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        val arr15min = ArrayList<UpDownDataSet>()
        val arr30min = ArrayList<UpDownDataSet>()
        val arr45min = ArrayList<UpDownDataSet>()

        // 2.
        for (data in selectedCoinList) {

            val coinName = data.coin_name  // coinName = BTC

            val oneCoinData =
                dbRepository.getOneSelectedCoinData(coinName)
                    .reversed() // 가장 마지막 값이 최신값이라 값을 거꾸로 가져옴


            val size = oneCoinData.size

            if (size > 1) {
                // DB에 값이 2개 이상은 있다.
                // 현재와 15분전 가격을 비교하려면 데이터가 2개는 있어야 함

                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[1].price.toDouble()
                val upDownDataSet = UpDownDataSet(
                    coinName,
                    changedPrice.toString()

                )
                arr15min.add(upDownDataSet)


            }
            if (size > 2) {
                // DB에 값이 3개 이상은 있다.
                // 현재와 30분전 가격을 비교하려면 데이터가 3개는 있어야 함
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[2].price.toDouble()
                val upDownDataSet = UpDownDataSet(
                    coinName,
                    changedPrice.toString()
                )
                arr30min.add(upDownDataSet)


            }
            if (size > 3) {
                // DB에 값이 4개 이상은 있다.
                // 현재와 45분전 가격을 비교하려면 데이터가 3개는 있어야 함
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[3].price.toDouble()
                val upDownDataSet = UpDownDataSet(
                    coinName,
                    changedPrice.toString()
                )
                arr45min.add(upDownDataSet)
            }


        }

        withContext(Dispatchers.Main){
        _arr15min.value = arr15min
        _arr30min.value = arr30min
        _arr45min.value = arr45min

        }


    }

}