package com.yw.customimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


/**
 * 自定义圆角图片
 * create by yangwei
 * on 2019-11-18 15:44
 */
public class CircleImageView extends AppCompatImageView {
    private Paint mPaint;//画笔
    private float radius;//内切圆的半径
    private float scale;//缩放比例
    private float width;//布局宽度
    private float height;//布局高度
    private Matrix matrix;

    public CircleImageView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//设置抗锯齿
        matrix = new Matrix();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//设置抗锯齿
        matrix = new Matrix();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * View中的测量方法
     * 用在次此处是要制作一个宽高一致的矩形，用来裁剪出来一个圆形
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        float w = Math.min(width, width);//选择一个最小的作为宽度
        radius = w / 2;
        //由于是圆形，所以宽高需要一致
        setMeasuredDimension((int) w, (int) w);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if (null != drawable) {
            //初始化BitmapShader，传入bitmap对象
//            mPaint.setShader(initBitmapShader(((BitmapDrawable) drawable)));
            mPaint.setShader(initBitmapShader(getBitmapFromDrawable(drawable)));
            //画圆形，指定好坐标，半径，画笔
            canvas.drawCircle(width / 2, height / 2, radius, mPaint);
            return;
        } else {

            super.onDraw(canvas);
        }


    }

    /**
     * 初始化着色器
     *
     * @param drawable
     * @return
     */
    private BitmapShader initBitmapShader(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        //设置着色器
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //此处width和height必须是float的，要不然会得出一个0
        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());//计算缩放比例，防止图片拉伸
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }

    private BitmapShader initBitmapShader(Bitmap bitmap) {
        //设置着色器
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //此处width和height必须是float的，要不然会得出一个0
        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());//计算缩放比例，防止图片拉伸
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
