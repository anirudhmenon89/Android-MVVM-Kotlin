package com.imageapplication.anirudhmenon.wundercar.ui.carmap

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.imageapplication.anirudhmenon.wundercar.BR
import com.imageapplication.anirudhmenon.wundercar.R
import com.imageapplication.anirudhmenon.wundercar.databinding.ActivityCarMapBinding
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseActivity
import com.imageapplication.anirudhmenon.wundercar.ui.utils.ViewModelProviderFactory
import com.google.android.gms.maps.model.CameraPosition
import javax.inject.Inject


class CarMapActivity: BaseActivity<ActivityCarMapBinding, CarMapViewModel>(), OnMapReadyCallback, CarMapNavigator {

    private var clickLat: Double = 0.0
    private var clickLong: Double = 0.0

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var carMapViewModel: CarMapViewModel

    private lateinit var carMapBinding: ActivityCarMapBinding

    private lateinit var googleMap: GoogleMap

    companion object {
        val CO_ORDINATE_LAT = "co_ordinate_lat"
        val CO_ORDINATE_LONG = "co_ordinate_long"

        /**
         * Use this method if you need to start CarMapActivity from anywhere
         * Use default values for parameters if you need to pass extras to this intent
         */
        fun newIntent(context: Context, lat: Double? = null, long: Double? = null): Intent {
            val intent = Intent(context, CarMapActivity::class.java)
            intent.putExtra(CO_ORDINATE_LAT, lat)
            intent.putExtra(CO_ORDINATE_LONG, long)
            return intent
        }
    }

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initVars()
        getDataFromExtra()

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
        viewModelFactory = ViewModelProviderFactory(carMapViewModel);
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

    private fun getDataFromExtra() {
        if (intent != null) {
            clickLat = intent.getDoubleExtra(CO_ORDINATE_LAT, 0.0)
            clickLong = intent.getDoubleExtra(CO_ORDINATE_LONG, 0.0)
        }
    }

    private fun zoomToMap(point: LatLng) {
        val cameraPosition = CameraPosition.Builder()
                .target(point)
                .zoom(17f)
                .build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    //endregion

    //region Overriden methods from OnMapReadyCallback
    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            this.googleMap = googleMap
            carMapViewModel.parseMarkers()
        }
    }
    //endregion

    //region Overriden methods from CarMapNavigator
    override fun renderMarkerOnMap(list: MutableList<CoordinateData>) {
        list.iterator().forEach {

            val point = LatLng(it.lat, it.long)
            val options = MarkerOptions()
            options.position(point)
            options.title(it.title)
            googleMap.addMarker(options)

            if (it.lat == clickLat && it.long == clickLong) {
                zoomToMap(point)
            }
        }
    }

    override fun handleError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
}

