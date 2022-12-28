package com.application.coincloneapp.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.application.coincloneapp.db.entity.SelectedCoinPriceEntity
import com.application.coincloneapp.network.model.RecentCoinPriceList
import com.application.coincloneapp.repository.DBRepository
import com.application.coincloneapp.repository.NetWorkRepository
import timber.log.Timber
import java.util.*


// 최근 거래된 코인 가격 내역을 가져오는 WorkManager

// 관심있는 코인의 가격 변동 정보를 가져와서 DB에 저장

class GetCoinPriceRecentWorkManager(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private val dbRepository = DBRepository()
    private val netWorkRepository = NetWorkRepository()


    override suspend fun doWork(): Result {

        getAllInterestSelectedCoinData()
        return Result.success()


    }

    suspend fun getAllInterestSelectedCoinData() {

        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()
        val timeStamp = Calendar.getInstance().time

        for (coinData in selectedCoinList) {
            Timber.d(coinData.toString())

            val recentCoinPriceList = netWorkRepository.getInterestCoinPriceData(coinData.coin_name)

            saveSelectedCoinPrice(
                coinData.coin_name,
                recentCoinPriceList,
                timeStamp
            )


        }


    }


    fun saveSelectedCoinPrice(
        coinName: String,
        recentCoinPriceList: RecentCoinPriceList,
        timeStamp: Date

    ) {
        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_data,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timeStamp
        )
        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)

    }

}