package com.hymane.etvexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hymane.expandtextview.ExpandTextView;
import com.hymane.expandtextview.OnReadMoreClickListener;
import com.hymane.expandtextview.utils.DensityUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ExpandTextView etv;
    private SeekBar sb_duration;
    private Button btn_modify_content;
    private Button btn_modify_icon;
    private SeekBar sb_min_line;
    private boolean img;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etv = (ExpandTextView) findViewById(R.id.etv);
        sb_duration = (SeekBar) findViewById(R.id.sb_duration);
        btn_modify_content = (Button) findViewById(R.id.btn_modify_content);
        btn_modify_icon = (Button) findViewById(R.id.btn_modify_icon);
        sb_min_line = (SeekBar) findViewById(R.id.sb_min_line);
        btn_modify_content.setOnClickListener(this);
        btn_modify_icon.setOnClickListener(this);


//        etv.setIndicateImage(R.drawable.ic_arrow_down_light_round);
//        etv.setMinVisibleLines(5);
//        etv.setContentTextSize(15);
//        etv.setTitleTextSize(16);
//        etv.setHintTextSize(12);
        etv.setContent("哈氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码哈撒打算的家具是大家就圣诞节氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码哈撒打算的家具是大家就圣诞节氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码哈撒打算的家具是大家就圣诞节氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码");
//        etv.setHintTextColor(Color.parseColor("#913242"));
        etv.setOnReadMoreListener(new OnReadMoreClickListener() {
            @Override
            public void onExpand() {
                Toast.makeText(MainActivity.this, "expand", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFold() {
                Toast.makeText(MainActivity.this, "fold", Toast.LENGTH_SHORT).show();
            }
        });
        sb_duration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                etv.setAnimationDuration(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_min_line.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                etv.setMinVisibleLines(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_modify_content) {
            final EditText editText = new EditText(this);
            editText.setLines(4);
            new AlertDialog.Builder(this)
                    .setTitle("内容")
                    .setView(editText, DensityUtils.dp2px(this, 16), DensityUtils.dp2px(this, 16), DensityUtils.dp2px(this, 16), DensityUtils.dp2px(this, 16))
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            etv.setContent(editText.getText().toString());
                        }
                    }).create().show();
        } else if (view.getId() == R.id.btn_modify_icon) {
            if (img) {
                etv.setIndicateImage(R.drawable.ic_keyboard_arrow_down_black_24dp);
            } else {
                etv.setIndicateImage(R.drawable.ic_arrow_down_light_round);
            }
            img = !img;
        }
    }
}
