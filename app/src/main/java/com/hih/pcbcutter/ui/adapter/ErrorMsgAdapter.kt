package com.shoulashou.demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hih.pcbcutter.R
import com.hih.pcbcutter.ui.bean.ErrorMsg


/**
 *Created by likeye on 2020/8/26 15:25.
 **/
class ErrorMsgAdapter1 (val list:List<ErrorMsg>):RecyclerView.Adapter<ErrorMsgAdapter1.ViewHolder>(){
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val name:TextView=view.findViewById(R.id.error_msg)
        val num:TextView=view.findViewById(R.id.num)
        val time:TextView=view.findViewById(R.id.date_time)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrorMsgAdapter1.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_error_record,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ErrorMsgAdapter1.ViewHolder, position: Int) {
        val msg=list[position]
        holder.name.text=msg.msg
        holder.num.text=msg.num
        holder.time.text=msg.time
    }

}