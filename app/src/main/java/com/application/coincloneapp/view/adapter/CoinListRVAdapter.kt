package com.application.coincloneapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.coincloneapp.R
import com.application.coincloneapp.db.entity.InterestedCoinEntity

class CoinListRVAdapter(val context: Context, val dataSet: List<InterestedCoinEntity>) :
    RecyclerView.Adapter<CoinListRVAdapter.ViewHolder>() {


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_coin_item,parent,false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

        return dataSet.size

        }

    }
