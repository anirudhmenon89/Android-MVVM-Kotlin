package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imageapplication.anirudhmenon.wundercar.R
import com.imageapplication.anirudhmenon.wundercar.databinding.ItemCarListBinding
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseViewHolder
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo
import kotlinx.android.synthetic.main.item_car_list.view.*

class CarListAdapter(carList: ArrayList<CarInfo>) : RecyclerView.Adapter<CarListAdapter.CarListHolder>() {

    private var carList: ArrayList<CarInfo>? = carList

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CarListHolder {
        val blogViewBinding = ItemCarListBinding.inflate(LayoutInflater.from(parent!!.getContext()),
                parent, false)
        return CarListHolder(blogViewBinding)
    }

    override fun getItemCount() = if (carList == null) 0 else carList!!.size

    override fun onBindViewHolder(holder: CarListHolder?, position: Int) {
        holder!!.bind(position)
    }

    /**
     * Call this method if you need to change data at runtime
     */
    fun setData(carList: ArrayList<CarInfo>) {
        this.carList = carList
        notifyDataSetChanged()
    }

    inner class CarListHolder: BaseViewHolder, CarListItemViewModel.CarListItemClick {

        private lateinit var carItemViewModel : CarListItemViewModel
        private var viewBinding: ItemCarListBinding

        constructor(viewBinding: ItemCarListBinding): super(viewBinding.root) {
            this.viewBinding = viewBinding
        }

        override fun bind(position: Int) {
            val carInfo = carList!!.get(position)
            carItemViewModel = CarListItemViewModel(carInfo, this)
            viewBinding.viewModel = carItemViewModel
            viewBinding.executePendingBindings()
        }

        override fun onItemClick() {
            Log.i("TAG,", "User clicks on item")
        }

    }
}