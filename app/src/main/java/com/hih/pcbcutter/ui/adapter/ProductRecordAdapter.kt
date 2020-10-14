package com.shoulashou.demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hih.pcbcutter.R
import com.hih.pcbcutter.ui.bean.ProductRecord


/**
 *Created by likeye on 2020/8/26 16:12.
 **/
class ProductRecordAdapter1(val list:List<ProductRecord>):RecyclerView.Adapter<ProductRecordAdapter1.ViewHolder>(){
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val name:TextView=view.findViewById(R.id.item)
        val num:TextView=view.findViewById(R.id.num)
        val time:TextView=view.findViewById(R.id.date)
        val start:TextView=view.findViewById(R.id.start_time)
        val end:TextView=view.findViewById(R.id.end_time)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductRecordAdapter1.ViewHolder {
        val  view=LayoutInflater.from(parent.context).inflate(R.layout.item_product_record,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ProductRecordAdapter1.ViewHolder, position: Int) {
        val product=list[position]
        holder.name.text=product.message
        holder.num.text=product.id.toString()
        holder.start.text=product.start
        holder.end.text=product.end
        holder.time.text=product.date
    }

}