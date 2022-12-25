package com.application.coincloneapp.repository

import com.application.coincloneapp.App
import com.application.coincloneapp.db.CoinPriceDatabase
import com.application.coincloneapp.db.entity.InterestedCoinEntity

class DBRepository {


    val context = App.context()
    val db = CoinPriceDatabase.getDatabase(context)


    // InterestCoin

    // 전체 코인 데이터 가져오기
    fun getAllInterestCoinData() = db.interestCoinDAO().getAllData()


    // 코인 데이터 넣기
    fun insertInterestCoinData(interestCoinEntity: InterestedCoinEntity) =
        db.interestCoinDAO().insert(interestCoinEntity)

    // 코인 데이터 업데이트
    fun updateInterestCoinData(interestCoinEntity: InterestedCoinEntity) =
        db.interestCoinDAO().update(interestCoinEntity)


    // 사용자가 관심있어한 코인만 가져오기
    fun getAllInterestSelectedCoinData() = db.interestCoinDAO().getSelectedData()


}