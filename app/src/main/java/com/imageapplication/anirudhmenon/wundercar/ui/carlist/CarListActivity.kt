package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.imageapplication.anirudhmenon.wundercar.BR
import com.imageapplication.anirudhmenon.wundercar.R
import com.imageapplication.anirudhmenon.wundercar.databinding.ActivityCarListBinding
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseActivity
import com.imageapplication.anirudhmenon.wundercar.ui.utils.ViewModelProviderFactory

class CarListActivity : BaseActivity<ActivityCarListBinding, CarListViewModel>(), CarListNavigator {

    private lateinit var carListViewModel: CarListViewModel

    private lateinit var carListBinding: ActivityCarListBinding;

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVars()
        carListViewModel.getListOfCars()

        setListAdapter()
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_car_list
    }

    override fun getViewModel(): CarListViewModel {
        viewModelFactory = ViewModelProviderFactory(CarListViewModel());
        carListViewModel = ViewModelProviders.of(this, viewModelFactory).get(CarListViewModel::class.java!!)
        return carListViewModel
    }

    private fun initVars() {
        carListBinding = getViewDataBinding()
    }

    private fun setListAdapter() {
        carListBinding.rvCarList.layoutManager = LinearLayoutManager(this)
        carListBinding.rvCarList.adapter = CarListAdapter(carListViewModel.carDetails)

    }


    override fun openCarDetail() {
    }

    override fun handleError(throwable: Throwable) {
    }
}
