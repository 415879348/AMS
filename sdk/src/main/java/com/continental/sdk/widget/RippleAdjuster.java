package com.continental.sdk.widget;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;

import com.coorchice.library.SuperTextView;
import com.coorchice.library.SuperTextView.Adjuster;

import org.jetbrains.annotations.NotNull;

public final class RippleAdjuster extends Adjuster {
    private final float DEFAULT_RADIUS = 100.0F;
    private final PorterDuffXfermode xfermode;
    private final int rippleColor;
    private float touch_x;
    private float touch_y;
    private final Paint paint;
    private float density;
    private float radius;
    private final RectF rectF;
    private final float velocity;
    private Path solidPath;
    private RectF solidRectF;
    private Bitmap src;
    private Canvas srcCanvas;
    private Bitmap dst;
    private Canvas dstCanvas;

    public RippleAdjuster(int myRippleColor) {
        this.xfermode = new PorterDuffXfermode(Mode.SRC_IN);
        this.rippleColor = myRippleColor;
        this.touch_x = -1.0F;
        this.touch_y = -1.0F;
        this.paint = new Paint();
        this.rectF = new RectF();
        this.velocity = 1.123F;
        this.paint.setAntiAlias(true);
        this.paint.setColor(this.rippleColor);
    }

    @SuppressLint({"WrongConstant"})
    protected void adjust(@NotNull SuperTextView v, @NotNull Canvas canvas) {
        int width = v.getWidth();
        int height = v.getHeight();
        if (touch_x == -1f && touch_y == -1f) {
            return;
        }
        if (srcCanvas == null) {
            src = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            srcCanvas = new Canvas(src);
        }
        if (dstCanvas == null) {
            dst = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            dstCanvas = new Canvas(dst);
        }
        if (density == 0f) {
            density = v.getResources().getDisplayMetrics().density;
        }
        if (touch_x == -1f) {
            touch_x = width / 2f;
        }
        if (touch_y == -1f) {
            touch_y = height / 2f;
        }

        if (radius < width * 1.1f) {
            if (radius < DEFAULT_RADIUS) {
                radius += 20;
            } else {
                radius *= velocity;
            }
        } else {
            v.stopAnim();
        }
        rectF.setEmpty();
        rectF.set(0f, 0f, width, height);

        if (solidPath == null) {
            solidPath = new Path();
        } else {
            solidPath.reset();
        }
        if (solidRectF == null) {
            solidRectF = new RectF();
        } else {
            solidRectF.setEmpty();
        }
        float strokeWidth = v.getStrokeWidth();
        solidRectF.set(strokeWidth, strokeWidth, v.getWidth() - strokeWidth,
                v.getHeight() - strokeWidth);
        solidPath.addRoundRect(solidRectF, v.getCorners(), Path.Direction.CW);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        srcCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        srcCanvas.drawPath(solidPath, paint);

        paint.setColor(rippleColor);
        dstCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        dstCanvas.drawCircle(touch_x, touch_y, radius * density, paint);
        // 创建一个图层，在图层上演示图形混合后的效果
        int sc = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sc = canvas.saveLayer(0f, 0f, width, height, null);
        } else {
            sc = canvas.saveLayer(0f, 0f, width, height, null, Canvas.ALL_SAVE_FLAG);
        }

        canvas.drawBitmap(src, 0f, 0f, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(dst, 0f, 0f, paint);

        paint.setXfermode(null);
        canvas.restoreToCount(sc);
    }

    public boolean onTouch(SuperTextView v, MotionEvent event) {
        int action = event.getAction();
        switch(action) {
            case 0:
                this.touch_x = event.getX();
                this.touch_y = event.getY();
                this.radius = 0.0F;
                v.setAutoAdjust(true);
                v.startAnim();
                break;
            case 1:
            case 3:
                v.stopAnim();
                v.setAutoAdjust(false);
            case 2:
        }
        return true;
    }

}
