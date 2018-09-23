package com.imageapplication.anirudhmenon.wundercar.ui.carmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class CarMapActivity: AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}