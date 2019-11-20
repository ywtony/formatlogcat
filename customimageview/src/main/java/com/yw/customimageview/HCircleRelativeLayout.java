package com.yw.customimageview;

import android.content.Context;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型图片堆叠效果，最后一张图片漏出其他遮住
 * create by yangwei
 * on 2019-11-18 21:31
 */
public class HCircleRelativeLayout extends RelativeLayout {
    private int width = 200;
    private int left = width / 2;
    private Context context;
    private int defaultIcon;

    public HCircleRelativeLayout(Context context) {
        super(context);
        this.context = context;
    }

    public HCircleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public HCircleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    public HCircleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    /**
     * 配置
     *
     * @param p     填充比例
     * @param width 图片宽度
     */
    public void setConfig(int p, int width, int defaultIcon) {
        this.width = width;
        this.left = width / p;
        this.defaultIcon = defaultIcon;

    }

    /**
     * 设置数据源
     *
     * @param datas 设置数据源
     */
    public void setList(List<String> datas) {
        if (datas == null) return;
        for (int i = 0; i < datas.size(); i++) {
            CircleImageView imageView = new CircleImageView(context);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, width);
            //params.width = width;
            //params.height = width;
            params.setMargins(left * i, 0, 0, 0);
            imageView.setLayoutParams(params);
//            imageView.setImageResource(R.drawable.girl);
            GlideUrl glideUrl = new GlideUrl(datas.get(i), new LazyHeaders.Builder()
                    .addHeader("User-Agent", "Mozilla/5.0 (android) GoogleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36")
                    .build());
            Glide.with(context).load(glideUrl).placeholder(defaultIcon).into(imageView);
            this.addView(imageView);
        }
    }

    /**
     * 定义属性
     * 1.填充所占宽度的比例
     * 2.图片的宽高
     * 3.图片的数量
     */
}
