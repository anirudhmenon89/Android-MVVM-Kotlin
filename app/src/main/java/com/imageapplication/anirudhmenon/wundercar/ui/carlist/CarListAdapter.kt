package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imageapplication.anirudhmenon.wundercar.R
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo
import kotlinx.android.synthetic.main.item_car_list.view.*

class CarListAdapter(carList: ArrayList<CarInfo>) : RecyclerView.Adapter<CarListAdapter.CarListHolder>() {

    private var carList: ArrayList<CarInfo>? = carList

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CarListHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        return CarListHolder(inflater.inflate(R.layout.item_car_list, parent, false))
    }

    override fun getItemCount() = if (carList == null) 0 else carList!!.size

    override fun onBindViewHolder(holder: CarListHolder?, position: Int) {
        holder!!.bind(carList!!.get(position))
    }

    fun setData(carList: ArrayList<CarInfo>) {
        this.carList = carList
        notifyDataSetChanged()
    }

    class CarListHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
        fun bind(carInfo: CarInfo) {
            itemView.tv_car_name.text = carInfo.name
        }

    }
}