package com.hymane.expandtextview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hymane.expandtextview.utils.DensityUtils;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/8/12
 * Description: 可折叠的textview控件，点击下拉按钮可展开textview全部内容，再次点击收回。
 */
public class ExpandTextView extends LinearLayout implements View.OnClickListener {
    public static final String TAG = "ExpandTextView";


    public static final int DEFAULT_TITLE_TEXT_COLOR = 0X8A000000;
    public static final int DEFAULT_CONTENT_TEXT_COLOR = 0XDE000000;
    public static final int DEFAULT_HINT_TEXT_COLOR = 0XDE000000;
    public static final int DEFAULT_MIN_LINES = 4;
    public static final int DEFAULT_TITLE_TEXT_SIZE = 16;
    public static final int DEFAULT_CONTENT_TEXT_SIZE = 14;
    public static final int DEFAULT_HINT_TEXT_SIZE = 12;
    public static final int DEFAULT_ANIMATION_DURATION = 600;

    private Context mContext;
    //标题
    private String title;
    //内容文字
    private String content;
    //展开后显示的文字
    private String foldHint;
    //折叠起来后显示的文字
    private String expandHint;
    //标题文字颜色
    private int titleTextColor;
    //内容文字颜色
    private int contentTextColor;
    //查看更多文字颜色
    private int hintTextColor;
    //查看更多前面显示的小图标
    private Drawable indicateImage;
    //内容区域最少显示几行文字
    private int minVisibleLines;
    //标题字体大小
    private float titleTextSize;
    //内容字体大小
    private float contentTextSize;
    //提示字体大小
    private float hintTextSize;
    //展开和折叠动画持续时间
    private int animationDuration;

    private TextView mTitleView;
    private TextView mContentView;
    private TextView mHintView;
    private ImageView mIndicateImage;
    private RelativeLayout mShowMore;
    private TextView copy;

    private OnReadMoreClickListener mListener;

    //      是否已经展开
    private boolean flag;

    public ExpandTextView(Context context) {
        this(context, null);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        title = a.getString(R.styleable.ExpandTextView_titleText);
        content = a.getString(R.styleable.ExpandTextView_contentText);
        foldHint = a.getString(R.styleable.ExpandTextView_foldHint);
        expandHint = a.getString(R.styleable.ExpandTextView_expandHint);
        titleTextColor = a.getColor(R.styleable.ExpandTextView_textTitleColor, DEFAULT_TITLE_TEXT_COLOR);
        contentTextColor = a.getColor(R.styleable.ExpandTextView_textContentColor, DEFAULT_CONTENT_TEXT_COLOR);
        hintTextColor = a.getColor(R.styleable.ExpandTextView_textHintColor, DEFAULT_HINT_TEXT_COLOR);
        indicateImage = a.getDrawable(R.styleable.ExpandTextView_indicateImage);
        minVisibleLines = a.getInt(R.styleable.ExpandTextView_minVisibleLines, DEFAULT_MIN_LINES);
        titleTextSize = a.getDimension(R.styleable.ExpandTextView_titleTextSize, DensityUtils.sp2px(mContext, DEFAULT_TITLE_TEXT_SIZE));
        contentTextSize = a.getDimension(R.styleable.ExpandTextView_contentTextSize, DensityUtils.sp2px(mContext, DEFAULT_CONTENT_TEXT_SIZE));
        hintTextSize = a.getDimension(R.styleable.ExpandTextView_hintTextSize, DensityUtils.sp2px(mContext, DEFAULT_HINT_TEXT_SIZE));
        animationDuration = a.getInt(R.styleable.ExpandTextView_animationDuration, DEFAULT_ANIMATION_DURATION);

        a.recycle();
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        View.inflate(mContext, R.layout.expand_text_view, this);
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mContentView = (TextView) findViewById(R.id.tv_content);
        mHintView = (TextView) findViewById(R.id.tv_more_hint);
        mIndicateImage = (ImageView) findViewById(R.id.iv_arrow_more);
        mShowMore = (RelativeLayout) findViewById(R.id.rl_show_more);

        mTitleView.setText(title);
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        mTitleView.setTextColor(titleTextColor);

        mContentView.setText(content);
        mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentTextSize);
        mContentView.setTextColor(contentTextColor);

        mHintView.setText(expandHint);
        mHintView.setTextSize(TypedValue.COMPLEX_UNIT_PX, hintTextSize);
        mHintView.setTextColor(hintTextColor);

        if (indicateImage == null) {
            indicateImage = getResources().getDrawable(R.drawable.ic_arrow_down_light_round);
        }
        mIndicateImage.setImageDrawable(indicateImage);
        mShowMore.setOnClickListener(this);
        ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        layoutParams.height = getMinMeasureHeight();
        mContentView.setLayoutParams(layoutParams);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        mTitleView.setText(title);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        mContentView.setText(content);
    }

    public String getFoldHint() {
        return foldHint;
    }

    public void setFoldHint(String foldHint) {
        this.foldHint = foldHint;
    }

    public String getExpandHint() {
        return expandHint;
    }

    public void setExpandHint(String expandHint) {
        this.expandHint = expandHint;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(@ColorInt int titleTextColor) {
        this.titleTextColor = titleTextColor;
        mTitleView.setTextColor(titleTextColor);
    }

    public int getContentTextColor() {
        return contentTextColor;
    }

    public void setContentTextColor(@ColorInt int contentTextColor) {
        this.contentTextColor = contentTextColor;
        mContentView.setTextColor(contentTextColor);
    }

    public int getHintTextColor() {
        return hintTextColor;
    }

    public void setHintTextColor(@ColorInt int hintTextColor) {
        this.hintTextColor = hintTextColor;
        mHintView.setTextColor(hintTextColor);
    }

    public Drawable getIndicateImage() {
        return indicateImage;
    }

    public void setIndicateImage(@DrawableRes Drawable indicateImage) {
        this.indicateImage = indicateImage;
        mIndicateImage.setImageDrawable(indicateImage);
    }

    public void setIndicateImage(@DrawableRes int indicateImageRes) {
        this.indicateImage = getResources().getDrawable(indicateImageRes);
        mIndicateImage.setImageDrawable(this.indicateImage);
    }

    public int getMinVisibleLines() {
        return minVisibleLines;
    }

    public void setMinVisibleLines(int minVisibleLines) {
        this.minVisibleLines = minVisibleLines;
        copy = null;
        ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        layoutParams.height = getMinMeasureHeight();
        mContentView.setLayoutParams(layoutParams);
    }

    public float getTitleTextSize() {
        return titleTextSize;
    }

    /**
     * 设置字体大小
     *
     * @param titleTextSize sp为单位
     */
    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = DensityUtils.sp2px(mContext, titleTextSize);
        mTitleView.setTextSize(titleTextSize);
    }

    public float getContentTextSize() {
        return contentTextSize;
    }

    /**
     * 设置字体大小
     *
     * @param contentTextSize sp为单位
     */
    public void setContentTextSize(float contentTextSize) {
        this.contentTextSize = DensityUtils.sp2px(mContext, contentTextSize);
        mContentView.setTextSize(contentTextSize);
        copy = null;
        ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        layoutParams.height = getMinMeasureHeight();
        mContentView.setLayoutParams(layoutParams);
    }

    public float getHintTextSize() {
        return hintTextSize;
    }

    /**
     * 设置字体大小
     *
     * @param hintTextSize sp为单位
     */
    public void setHintTextSize(float hintTextSize) {
        this.hintTextSize = DensityUtils.sp2px(mContext, hintTextSize);
        mHintView.setTextSize(hintTextSize);
    }

    public View getTitleView() {
        return mTitleView;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
    }

    public OnReadMoreClickListener getReadMoreListener() {
        return mListener;
    }

    public void setOnReadMoreListener(OnReadMoreClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_show_more) {
            expand();
        }
    }

    /**
     * 获取TextView最大高度
     *
     * @return int 最大测量高度
     */
    public int getMaxMeasureHeight() {
        int width = mContentView.getMeasuredWidth();
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(3000, View.MeasureSpec.AT_MOST);
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        return mContentView.getMeasuredHeight();
    }

    /**
     * 获取TextView最小高度
     *
     * @return int 最小测量高度
     */
    public int getMinMeasureHeight() {
        if (copy == null) {
            copy = new TextView(mContext);
            copy.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentTextSize);
            copy.setLineSpacing(DensityUtils.dp2px(mContext, 6), 1.0f);
            copy.setLines(minVisibleLines);
        }
        int width = mContentView.getMeasuredWidth();
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);
        copy.measure(widthMeasureSpec, heightMeasureSpec);
        return copy.getMeasuredHeight();
    }

    /**
     * 折叠和展开
     */
    private void expand() {
        int startHeight;
        int targetHeight;
        if (!flag) {
            flag = true;
            startHeight = getMinMeasureHeight();
            targetHeight = getMaxMeasureHeight();
            if (targetHeight < startHeight) {
                targetHeight = startHeight;
            }
            mHintView.setText(foldHint);
            ObjectAnimator.ofFloat(mIndicateImage, "rotation", 0, 180).start();
            if (mListener != null) {
                mListener.onExpand();
            }
        } else {
            flag = false;
            startHeight = getMaxMeasureHeight();
            targetHeight = getMinMeasureHeight();
            if (startHeight < targetHeight) {
                startHeight = targetHeight;
            }
            mHintView.setText(expandHint);
            ObjectAnimator.ofFloat(mIndicateImage, "rotation", -180, 0).start();
            if (mListener != null) {
                mListener.onFold();
            }
        }
        if (animationDuration < 0) {
            animationDuration = DEFAULT_ANIMATION_DURATION;
        }
        final ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(startHeight, targetHeight);
        animator.setDuration(animationDuration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.height = (int) animation.getAnimatedValue();
                mContentView.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }
}
