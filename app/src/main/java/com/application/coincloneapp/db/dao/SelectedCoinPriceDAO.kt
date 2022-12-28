package com.application.coincloneapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.coincloneapp.db.entity.SelectedCoinPriceEntity


@Dao
interface SelectedCoinPriceDAO  {

    @Query("SELECT * FROM selected_coin_price_table")
    fun getAllData(): List<SelectedCoinPriceEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(selectedCoinPriceEntity: SelectedCoinPriceEntity)


    // BTC 15 30 45 ->  List<BTC> -> 현재가격 15 30 45 어떻게 변화했는지 DB에 저장된 값과 비교하는 용도
    @Query("SELECT * FROM selected_coin_price_table WHERE coinName = :coinName")
    fun getOneCoinData(coinName: String) : List<SelectedCoinPriceEntity>



}