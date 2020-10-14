package com.hih.pcbcutter.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hih.pcbcutter.R;
import com.hih.pcbcutter.tcp.ApplicationUtil;
import com.hih.pcbcutter.ui.bean.DataSet;

import java.util.List;

/**
 * Created by likeye on 2020/8/6 11:38.
 **/
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private final List<DataSet> list;
    private final ApplicationUtil appUtil;

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView setTT;
        final ImageButton setL;
        final SeekBar setSpinner;
        final ImageButton setR;
        final TextView setT;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            setTT = itemView.findViewById(R.id.set_t_t);
            setL = itemView.findViewById(R.id.set_l);
            setSpinner = itemView.findViewById(R.id.set_spinner);
            setR = itemView.findViewById(R.id.set_r);
            setT = itemView.findViewById(R.id.set_t);
        }
    }

    public DataAdapter(List<DataSet> dataSets, ApplicationUtil applicationUtil) {
        list = dataSets;
        appUtil=applicationUtil;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataSet dataSet = list.get(position);
        holder.setTT.setText(dataSet.getName());
        holder.setSpinner.setProgress(Integer.parseInt(dataSet.getNum()));
        holder.setSpinner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.setT.setText(""+(holder.setSpinner.getProgress()));
                list.get(position).setNum(""+holder.setSpinner.getProgress());
                appUtil.sendData(dataSet.getName()+";"+holder.setSpinner.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        holder.setT.setText(""+(holder.setSpinner.getProgress()));
        holder.setL.setOnClickListener(v -> {
            if(holder.setSpinner.getProgress()>0) {
                holder.setSpinner.setProgress(holder.setSpinner.getProgress() - 1);
                holder.setT.setText("" + (holder.setSpinner.getProgress()));
                list.get(position).setNum(holder.setSpinner.getProgress()+"");
            }
        });
        holder.setR.setOnClickListener(v -> {
            if(holder.setSpinner.getProgress()<100) {
                holder.setSpinner.setProgress(holder.setSpinner.getProgress() + 1);
                holder.setT.setText("" + (holder.setSpinner.getProgress()));
                list.get(position).setNum(holder.setSpinner.getProgress()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<DataSet> getList(){
        return list;
    }

}
