package com.hedan.textdrawablelibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by DengXiao on 2017/4/7.
 */

public class TextViewDrawable extends AppCompatTextView {


    /**
     * 是否居中对齐
     */
    private boolean isAlignCenter = true;

    private Context mContext;
    private DrawableSize mDrawablesSize[] = new DrawableSize[4];


    public TextViewDrawable(Context context) {
        this(context, null);
    }

    public TextViewDrawable(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Drawable[] drawables = getCompoundDrawables();
        for (int i = 0; i < drawables.length; i++) {
            boolean isVerticalCenter = i % 2 == 0;
            layoutDrawable(drawables[i], mDrawablesSize[i], isVerticalCenter);
        }
        this.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);

    }

    private void layoutDrawable(Drawable drawable, DrawableSize drawableSize, boolean isVerticalCenter) {
        if (drawable == null) return;
        int width = drawableSize.width == 0 ? drawable.getIntrinsicWidth() : drawableSize.width;
        int height = drawableSize.height == 0 ? drawable.getIntrinsicHeight() : drawableSize.height;
        int left, top, right, bottom;
        if (isVerticalCenter) {
            left = 0;
            top = isAlignCenter ? 0 : -getLineCount() * getLineHeight() / 2 + getLineHeight() / 2;
            right = width;
            bottom = top + height;
        } else {
            left = isAlignCenter ? 0 : width / 2;
            top = 0;
            right = left + width;
            bottom = top + height;
        }
        drawable.setBounds(left, top, right, bottom);

    }

    private void initView(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TextViewDrawable);
        // 左边的宽度和高度
        int drawableLeftWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableLeftWidth, 0);
        int drawableLeftHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableLeftHeight, 0);
        mDrawablesSize[0] = new DrawableSize(drawableLeftWidth, drawableLeftHeight);
        //上面宽度和高度
        int drawableTopWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableTopWidth, 0);
        int drawableTopHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableTopHeight, 0);
        mDrawablesSize[1] = new DrawableSize(drawableTopWidth, drawableTopHeight);
        //右边的宽度和高度
        int drawableRightWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableRightWidth, 0);
        int drawableRightHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableRightHeight, 0);
        mDrawablesSize[2] = new DrawableSize(drawableRightWidth, drawableRightHeight);
        //下边的宽度和高度
        int drawableBottomWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableBottomWidth, 0);
        int drawableBottomHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableBottomHeight, 0);
        mDrawablesSize[3] = new DrawableSize(drawableBottomWidth, drawableBottomHeight);
        isAlignCenter = typedArray.getBoolean(R.styleable.TextViewDrawable_isAliganCenter, true);
        typedArray.recycle();
    }


    static class DrawableSize {
        public int width;
        public int height;

        DrawableSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
