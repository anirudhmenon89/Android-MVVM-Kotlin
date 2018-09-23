package com.imageapplication.anirudhmenon.wundercar.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class ActionBar : RelativeLayout {

    private lateinit var title: TextView
    private lateinit var back: ImageView

    constructor(context: Context) : super(context){
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet):    super(context, attrs){
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?,    defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        initTitle(context, "Cars nearby")
        initBack(context, false)
        setPadding(0, 50, 0, 50)

    }

    private fun initTitle(context: Context, text: String) {

        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL)

        title = TextView(context)
        title.layoutParams = layoutParams
        title.text = text

        addView(title)
    }

    private fun initBack(context: Context, shouldShow: Boolean) {

    }

}