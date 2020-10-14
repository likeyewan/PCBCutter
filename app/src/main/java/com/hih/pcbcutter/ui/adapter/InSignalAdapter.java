package com.hih.pcbcutter.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.ui.bean.Signal;

import java.util.List;

/**
 * Created by likeye on 2020/8/14 9:21.
 **/
public class InSignalAdapter  extends RecyclerView.Adapter<InSignalAdapter.ViewHolder> {
    private final ApplicationUtil appUtil;
    public static List<Signal> list;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        final CheckBox checkBox;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.signT);
            checkBox=itemView.findViewById(R.id.signC);
        }
    }

    public InSignalAdapter(List<Signal> signals, Activity activity){
        appUtil = (ApplicationUtil) activity.getApplication();
        list=signals;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sign,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Signal signal=list.get(position);
        holder.name.setText(signal.getName());
        holder.checkBox.setChecked(signal.getChoose());
        holder.checkBox.setOnClickListener(v -> {
            boolean isChecke=holder.checkBox.isChecked();
            if(isChecke){
                appUtil.sendData(signal.getName() + ";" + true);
                signal.setChoose(true);
                list.set(position, signal);
            }else {
                signal.setChoose(false);
                list.set(position,signal);
                appUtil.sendData(signal.getName()+";"+false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
