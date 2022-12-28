package com.application.coincloneapp.network.model

import com.application.coincloneapp.datamodel.RecentPriceData

data class RecentCoinPriceList (

    val status : String,
    val data : List<RecentPriceData>

        )
