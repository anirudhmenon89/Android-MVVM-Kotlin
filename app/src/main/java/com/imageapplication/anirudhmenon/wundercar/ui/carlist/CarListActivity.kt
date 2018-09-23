package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.imageapplication.anirudhmenon.wundercar.BR
import com.imageapplication.anirudhmenon.wundercar.R
import com.imageapplication.anirudhmenon.wundercar.databinding.ActivityCarListBinding
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseActivity
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview.CarListAdapter
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview.CarListItemViewModel
import com.imageapplication.anirudhmenon.wundercar.ui.utils.ViewModelProviderFactory

class CarListActivity : BaseActivity<ActivityCarListBinding, CarListViewModel>(), CarListNavigator, CarListAdapter.CarListListener {

    private lateinit var carListViewModel: CarListViewModel

    private lateinit var carListBinding: ActivityCarListBinding;

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    //region Activity lifecycle overridden methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVars()

        carListViewModel.getListOfCars()

        setListAdapter()
    }
    //endregion

    //region BaseActivity overridden methods
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
    //endregion

    private fun initVars() {
        carListBinding = getViewDataBinding()

        carListViewModel.setNavigator(this)
    }

    private fun setListAdapter() {
        carListBinding.rvCarList.layoutManager = LinearLayoutManager(this)
        carListBinding.rvCarList.adapter = CarListAdapter(carListViewModel.carDetails, this)

    }

    //region CarListAdapter.CarListListener overridden methods
    override fun onClick(view: View, viewModel: CarListItemViewModel) {
        // TODO Handle any navigation events if user clicks on a list item
        // This is not implemented currently because we do not have to solve such a problem statement
    }
    //endregion

    //region CarListNavigator overridden methods
    override fun openCarDetail() {
        Log.i("OPEN MAP", "Open map click")
    }

    override fun handleError(throwable: Throwable) {
    }
    // endregion
}
