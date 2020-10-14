package com.hih.pcbcutter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.ui.bean.ErrorMsg;

import java.util.List;

/**
 * Created by likeye on 2020/8/21 9:34.
 **/
public class ErrorMsgAdapter extends RecyclerView.Adapter<ErrorMsgAdapter.ViewHolder> {
    private final List<ErrorMsg> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView num;
        final TextView time;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.error_msg);
            num = itemView.findViewById(R.id.num);
            time = itemView.findViewById(R.id.date_time);

        }
    }

    public ErrorMsgAdapter(List<ErrorMsg> errorMsg) {
        list = errorMsg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ErrorMsg errorMsg = list.get(position);
        holder.name.setText(errorMsg.getMsg());
        holder.num.setText(errorMsg.getNum());
        holder.time.setText(errorMsg.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}