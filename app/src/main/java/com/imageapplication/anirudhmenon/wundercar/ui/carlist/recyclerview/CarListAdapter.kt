package com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imageapplication.anirudhmenon.wundercar.databinding.ItemCarListBinding
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseViewHolder
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo

class CarListAdapter(carList: ArrayList<CarInfo>, listener: CarListListener) : RecyclerView.Adapter<CarListAdapter.CarListHolder>() {

    private var carList = carList
    private var listener = listener

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CarListHolder {
        val blogViewBinding = ItemCarListBinding.inflate(LayoutInflater.from(parent!!.getContext()),
                parent, false)
        return CarListHolder(blogViewBinding)
    }

    override fun getItemCount() = carList.size

    override fun onBindViewHolder(holder: CarListHolder?, position: Int) {
        holder!!.bind(position)
    }

    /**
     * The inner ViewHolder class for the recycler view.
     * This is responsible to bind the ViewModel to the item_car_list layout
     * and gives call backs when user clicks on a row
     */
    // region  ViewHolderClass
    inner class CarListHolder: BaseViewHolder, CarListItemViewModel.CarListItemClick {

        private lateinit var carItemViewModel : CarListItemViewModel
        private var viewBinding: ItemCarListBinding

        constructor(viewBinding: ItemCarListBinding): super(viewBinding.root) {
            this.viewBinding = viewBinding
        }

        override fun bind(position: Int) {
            val carInfo = carList[position]
            carItemViewModel = CarListItemViewModel(carInfo, this)
            viewBinding.viewModel = carItemViewModel
            viewBinding.executePendingBindings()
        }

        override fun onItemClick(view: View, viewModel: CarListItemViewModel) {
            listener.onClick(view, viewModel)
        }

    }
    // endregion

    // region Activity-Adapter Click interface
    interface CarListListener {
        fun onClick(view: View, viewModel: CarListItemViewModel)
    }
    // endregion
}