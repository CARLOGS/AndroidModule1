package com.dgtic.androidmodule1.ejercise.home.lissetnoriega.exercise2.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R

class ReservationAdapter (val list: List<ReservationEntity>): RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>(){
    class ReservationViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val tvCountry = view.findViewById<TextView>(R.id.tvCountry)
        val tvCity = view.findViewById<TextView>(R.id.tvCity)
        val tvNights = view.findViewById<TextView>(R.id.tvNumberNights)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)

        fun render(reservationEntity: ReservationEntity){
            tvCountry.text = reservationEntity.country
            tvCity.text = reservationEntity.city
            tvNights.text = reservationEntity.numberNights.toString()
            tvPrice.text = reservationEntity.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reservation,parent,false)
        return ReservationViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.render(list[position])
    }
}