# ExpandTextView
一个可折叠和展开的TextView控件

![image](https://github.com/Hymanme/ExpandTextView/raw/master/screenshots/show.gif)

![night](http://ww4.sinaimg.cn/mw690/005X6W83gw1f7lyq9c0slj30c00lc0u3.jpg)

##How to use
Add this to your build.gradle:
 
```
	dependencies {
	  compile 'com.hymane.expandtextview:library:1.0.2'
	}
```
## 更新日志
    3. 修复新版gradle编译之后点击展开崩溃
	2. 兼容夜间模式,字体颜色请使用主题对应的颜色
	1. 初版  
## 定义xml布局
		<com.hymane.expandtextview.ExpandTextView
            android:id="@+id/etv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingBottom="8dp"
            android:paddingLeft="@dimen/activity_horizontal_margin"
            android:paddingRight="@dimen/activity_horizontal_margin"
            android:paddingTop="8dp"
            app:contentTextSize="14sp"
            app:expandHint="点击展开"
            app:foldHint="点击收回"
            app:textContentColor="?android:attr/textColorPrimary"
            app:textHintColor="?android:attr/textColorPrimary"
            app:textTitleColor="?android:attr/textColorSecondary"
            app:titleText="标题党"
            app:titleTextSize="16sp">

##自定义设置

```Java
	etv.setIndicateImage(R.drawable.ic_arrow_down_light_round);
        etv.setMinVisibleLines(5);
        etv.setContentTextSize(15);
        etv.setTitleTextSize(16);
        etv.setHintTextSize(12);
        etv.setContent("text");
        etv.setHintTextColor(Color.parseColor("#913242"));
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
```

##可选项

    //设置标题
    public void setTitle(String title);
    //设置内容文本
    public void setContent(String content);
    //展开后显示的文字
    public void setFoldHint(String foldHint); 
    //折叠起来后显示的文字
    public void setExpandHint(String expandHint) ;
    //标题颜色
    public void setTitleTextColor(@ColorInt int titleTextColor);
    //内容字体颜色
    public void setContentTextColor(@ColorInt int contentTextColor);
    //查看更多字体颜色
    public void setHintTextColor(@ColorInt int hintTextColor);
    //查看更多前面小图标(夜间模式请更换对应图标，或者使用兼容主题的图标)
    public void setIndicateImage(@DrawableRes Drawable indicateImage);
    public void setIndicateImage(@DrawableRes int indicateImageRes);

    //最小显示行数
    public void setMinVisibleLines(int minVisibleLines);

    /**
     * 设置字体大小
     *
     * @param titleTextSize 单位为sp
     */
    public void setTitleTextSize(float titleTextSize);

    /**
     * 设置字体大小
     *
     * @param contentTextSize 单位为sp
     */
    public void setContentTextSize(float contentTextSize);
    }

    /**
     * 设置字体大小
     *
     * @param hintTextSize 单位为sp
     */
    public void setHintTextSize(float hintTextSize);

    /**
     * 动画执行时间
     *
     * @param animationDuration 单位毫秒
     */
    public void setAnimationDuration(int animationDuration);

    /**
     * 设置监听
     *
     * @param mListener 展开和收缩按钮点击时回调
     */
    public void setOnReadMoreListener(OnReadMoreClickListener mListener);
