package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.imageapplication.anirudhmenon.wundercar.BR
import com.imageapplication.anirudhmenon.wundercar.R
import com.imageapplication.anirudhmenon.wundercar.databinding.ActivityCarListBinding
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseActivity
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview.CarListAdapter
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview.CarListItemViewModel
import com.imageapplication.anirudhmenon.wundercar.ui.carmap.CarMapActivity
import com.imageapplication.anirudhmenon.wundercar.ui.utils.ViewModelProviderFactory
import javax.inject.Inject

class CarListActivity : BaseActivity<ActivityCarListBinding, CarListViewModel>(),
        CarListNavigator, CarListAdapter.CarListListener {

    @Inject
    lateinit var carListViewModel: CarListViewModel

    private lateinit var carListBinding: ActivityCarListBinding;

    private lateinit var viewModelFactory: ViewModelProvider.Factory


    //region Activity lifecycle overridden methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVars()

        carListViewModel.getListOfCars()

        setListAdapter()
    }

    override fun onStop() {
        super.onStop()
        removeObservers()
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
        viewModelFactory = ViewModelProviderFactory(carListViewModel);
        carListViewModel = ViewModelProviders.of(this, viewModelFactory).get(CarListViewModel::class.java)
        return carListViewModel
    }

    //endregion

    //region class functions

    private fun initVars() {
        carListBinding = getViewDataBinding()
        carListBinding.setLifecycleOwner(this)

        carListViewModel.setNavigator(this)
    }

    private fun setListAdapter() {
        carListBinding.rvCarList.layoutManager = LinearLayoutManager(this)
        carListBinding.rvCarList.adapter = CarListAdapter(carListViewModel.carDetails, this)

    }

    fun removeObservers() {
        carListViewModel.isLoading.removeObservers(this)
        carListViewModel.isError.removeObservers(this)
    }

    //endregion

    //region CarListAdapter.CarListListener overridden methods

    override fun onClick(view: View, viewModel: CarListItemViewModel) {
        startActivity(
                CarMapActivity.newIntent(this,
                        viewModel.coordinates[1],
                        viewModel.coordinates[0]
                )
        )
    }

    //endregion

    //region CarListNavigator overridden methods

    override fun openCarDetail() {
        TODO("No such functionality yet")
    }

    override fun handleError(throwable: Throwable) {
        TODO("Handle error from API here")
    }

    // endregion
}
