package com.imageapplication.anirudhmenon.wundercar.ui.carmap

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.imageapplication.anirudhmenon.wundercar.BR
import com.imageapplication.anirudhmenon.wundercar.R
import com.imageapplication.anirudhmenon.wundercar.databinding.ActivityCarMapBinding
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseActivity
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.CarListViewModel
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview.CarListAdapter
import com.imageapplication.anirudhmenon.wundercar.ui.utils.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_car_map.*

class CarMapActivity: BaseActivity<ActivityCarMapBinding, CarMapViewModel>(), OnMapReadyCallback, CarMapNavigator {

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var carMapViewModel: CarMapViewModel

    private lateinit var carMapBinding: ActivityCarMapBinding

    private lateinit var googleMap: GoogleMap

    companion object {
        /**
         * Use this method if you need to start CarMapActivity from anywhere
         * Use default values for parameters if you need to pass extras to this intent
         */
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, CarMapActivity::class.java)
            return intent
        }
    }

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVars()
        carMapBinding.mapView.onCreate(savedInstanceState)
        carMapBinding.mapView.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        carMapBinding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        carMapBinding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        carMapBinding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        carMapBinding.mapView.onStop()
    }

    override fun onDestroy() {
        carMapBinding.mapView.onDestroy()
        super.onDestroy()

    }
    //endregion

    //region BaseActivity overriden methods
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_car_map
    }

    override fun getViewModel(): CarMapViewModel {
        viewModelFactory = ViewModelProviderFactory(CarMapViewModel());
        carMapViewModel = ViewModelProviders.of(this, viewModelFactory).get(CarMapViewModel::class.java!!)
        return carMapViewModel
    }

    override fun onLowMemory() {
        super.onLowMemory()
        carMapBinding.mapView.onLowMemory()
    }
    //endregion

    //region class functions

    private fun initVars() {
        carMapBinding = getViewDataBinding()

        carMapViewModel.setNavigator(this)
    }

    //endregion

    //region Overriden methods from OnMapReadyCallback
    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {

            this.googleMap = googleMap
            carMapViewModel.parseMarkers()
//            var ny = LatLng(40.7143528, -74.0059731)
//            val marker = googleMap.addMarker(
//                    MarkerOptions()
//                            .position(ny)
//                            .title("New York"))
//            marker.isDraggable = true
//            marker.isVisible = true
//
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ny, 12F))
        }
    }
    //endregion

    //region Overriden methods from CarMapNavigator
    override fun renderMarkerOnMap(list: MutableList<CoordinateData>) {
        list.iterator().forEach {
            val point = LatLng(it.lat, it.long)
            val options = MarkerOptions()
            options.position(point)
            options.title("Some title")
            googleMap.addMarker(options)
        }
    }

    override fun handleError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
}

