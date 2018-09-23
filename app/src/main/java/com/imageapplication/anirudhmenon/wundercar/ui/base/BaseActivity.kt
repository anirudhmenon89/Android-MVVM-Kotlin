package com.imageapplication.anirudhmenon.wundercar.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.CarListNavigator
import dagger.android.AndroidInjection

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel<out BaseNavigator>> : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    private lateinit var viewDataBinding: DB
    private lateinit var viewModel: VM

    /**
     * Override this method to set binding variable
     */
    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    protected fun performDependencyInjection() {
        // AndroidInjection.inject(this)
    }

    protected fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = getViewModel()
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()

    }

    protected fun getViewDataBinding(): DB {
        return viewDataBinding
    }
}