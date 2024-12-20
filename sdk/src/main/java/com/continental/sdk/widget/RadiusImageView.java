package com.continental.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.blankj.utilcode.util.SizeUtils;
import com.continental.sdk.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 功能描述：一个简洁而高效的圆形ImageView
 *
 * @author (作者) edward（冯丰枫）
 * @link http://www.jianshu.com/u/f7176d6d53d2
 * 创建时间： 2018/4/17 0017
 */
public class RadiusImageView extends AppCompatImageView {
    private float width;
    private float height;
    private final float radius;
    private final Paint paint;
    private final Matrix matrix;

    public RadiusImageView(Context context) {
        this(context, null);
    }

    public RadiusImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadiusImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);   //设置抗锯齿
        matrix = new Matrix();      //初始化缩放矩阵
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RadiusImageView, defStyleAttr, 0);
        radius = attributes.getDimensionPixelSize(R.styleable.RadiusImageView_spsdk_imageRadius, SizeUtils.dp2px(10));
        attributes.recycle();
    }

    /**
     * 测量控件的宽高，并获取其内切圆的半径
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onDraw(canvas);
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            paint.setShader(initBitmapShader((BitmapDrawable) drawable));//将着色器设置给画笔
            //canvas.drawCircle(width / 2, height / 2, radius, paint);//使用画笔在画布上画圆
            canvas.drawRoundRect(0f, 0f, width, height, radius, radius, paint);
            return;
        }
        super.onDraw(canvas);
    }

    /**
     * 获取ImageView中资源图片的Bitmap，利用Bitmap初始化图片着色器,通过缩放矩阵将原资源图片缩放到铺满整个绘制区域，避免边界填充
     */
    private BitmapShader initBitmapShader(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());
        matrix.setScale(scale, scale);//将图片宽高等比例缩放，避免拉伸
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }
}
