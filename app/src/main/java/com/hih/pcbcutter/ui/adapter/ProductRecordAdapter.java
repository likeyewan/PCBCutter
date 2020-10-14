package com.hih.pcbcutter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.ui.bean.ProductRecord;

import java.util.List;

/**
 * Created by Administrator on 2020/7/22 15:39.
 **/
public class ProductRecordAdapter extends RecyclerView.Adapter<ProductRecordAdapter.ViewHolder> {
    private final List<ProductRecord> list;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        final TextView num;
        final TextView time;
        final TextView start;
        final TextView end;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item);
            num=itemView.findViewById(R.id.num);
            time=itemView.findViewById(R.id.date);
            start=itemView.findViewById(R.id.start_time);
            end=itemView.findViewById(R.id.end_time);
        }
    }
    public ProductRecordAdapter(List<ProductRecord> proudctRecords){
        list=proudctRecords;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_record,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductRecord proudctRecord=list.get(position);
        holder.name.setText(proudctRecord.getMessage());
        holder.num.setText(proudctRecord.getId()+"");
        holder.time.setText(proudctRecord.getDate());
        holder.start.setText(proudctRecord.getStart());
        holder.end.setText(proudctRecord.getEnd());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
