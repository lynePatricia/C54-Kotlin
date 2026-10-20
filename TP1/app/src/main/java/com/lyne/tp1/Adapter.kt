package com.lyne.tp1

import android.content.Context
import android.widget.ImageView
import android.widget.SimpleAdapter
import com.bumptech.glide.Glide

class Adapter (context: Context,
                     data: List<HashMap<String, Any>>,
                     resource: Int,
                     from: Array<String>,
                     to: IntArray) : SimpleAdapter(context, data, resource, from, to){

    override open fun setViewImage(
        v: ImageView,
        value: String
    ): Unit{
        Glide.with(v.context)
            .load(value)
            .into(v)
    }

}