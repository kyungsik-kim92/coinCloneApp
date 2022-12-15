package com.application.coincloneapp.repository

import com.application.coincloneapp.network.Api
import com.application.coincloneapp.network.RetrofitInstance

class NetWorkRepository{

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

}