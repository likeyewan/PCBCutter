package com.shoulashou.demo.kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hih.pcbcutter.R


/**
 *Created by likeye on 2020/8/26 16:36.
 **/
class RecordFragment:Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View=inflater.inflate(R.layout.fragment_record,container,false)
        return view
    }
}