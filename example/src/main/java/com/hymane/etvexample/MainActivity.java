package com.hymane.etvexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hymane.expandtextview.ExpandTextView;
import com.hymane.expandtextview.OnReadMoreClickListener;

public class MainActivity extends AppCompatActivity {
    private ExpandTextView etv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etv = (ExpandTextView) findViewById(R.id.etv);
        etv.setIndicateImage(R.drawable.ic_arrow_down_light_round);
        etv.setMinVisibleLines(5);
        etv.setContentTextSize(15);
        etv.setTitleTextSize(16);
        etv.setHintTextSize(12);
        etv.setContent("哈氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码哈撒打算的家具是大家就圣诞节氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码哈撒打算的家具是大家就圣诞节氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码哈撒打算的家具是大家就圣诞节氨基酸等今晚没空去骂死你的奶第七名大门的情况我们的马克思的门口吗傻么的空间马赛克默多克马赛克的吗萨姆的卡茅塞顿开马上买点卡莫斯科的马上发年初您请问你地加上就打卡开什么车市场价氨气味就代码可每次看目前我我解放你的是充满我看到静安寺的卡美森看代码");
        etv.setHintTextColor(Color.parseColor("#913242"));
        etv.setOnReadMoreListener(new OnReadMoreClickListener() {
            @Override
            public void onExpand() {
                Toast.makeText(MainActivity.this,"expand",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFold() {
                Toast.makeText(MainActivity.this,"fold",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
