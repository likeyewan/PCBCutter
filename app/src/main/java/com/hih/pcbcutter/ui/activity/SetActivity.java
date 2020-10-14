package com.hih.pcbcutter.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.hih.pcbcutter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likeye on 2020/8/4 17:31.
 **/
public class SetActivity extends AppCompatActivity {
    @BindView(R.id.set_spinner_ld)
    SeekBar setSpinnerLd;
    @BindView(R.id.set_ld_l)
    ImageButton setLdL;
    @BindView(R.id.set_ld_r)
    ImageButton setLdR;
    @BindView(R.id.set_t_ld)
    TextView setTLd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_set);
        ButterKnife.bind(this);
        setSpinnerLd.setOnSeekBarChangeListener(onSeekBarChangeListener);
        setSpinnerLd.setProgress(0);
        setTLd.setText(""+(setSpinnerLd.getProgress()));

    }

    private final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setTLd.setText(""+(setSpinnerLd.getProgress()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //Toast.makeText(SetActivity.this, "开始：" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //Toast.makeText(SetActivity.this, "结束：" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
        }
    };

    @OnClick({R.id.set_ld_l, R.id.set_ld_r})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_ld_l:
                if(setSpinnerLd.getProgress()>0) {
                    setSpinnerLd.setProgress(setSpinnerLd.getProgress() - 1);
                    setTLd.setText("" + (setSpinnerLd.getProgress()));
                }
                break;
            case R.id.set_ld_r:
                if(setSpinnerLd.getProgress()<100) {
                    setSpinnerLd.setProgress(setSpinnerLd.getProgress() + 1);
                    setTLd.setText("" + (setSpinnerLd.getProgress()));
                }
                break;
        }
    }
}
