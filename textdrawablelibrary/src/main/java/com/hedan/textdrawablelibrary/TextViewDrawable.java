package com.hedan.textdrawablelibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by DengXiao on 2017/4/7.
 */

public class TextViewDrawable extends AppCompatTextView {

    private int drawableLeftWidth, drawableTopWidth, drawableRightWidth, drawableBottomWidth;
    private int drawableLeftHeight, drawableTopHeight, drawableRightHeight, drawableBottomHeight;
    private boolean isAliganCenter = true;
    private boolean isDwMath_content = false;
    private int mWidth, mHeight;

    public TextViewDrawable(Context context) {
        this(context, null);
    }

    public TextViewDrawable(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewDrawable);
        drawableLeftWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableLeftWidth, 0);
        drawableTopWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableTopWidth, 0);
        drawableRightWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableRightWidth, 0);
        drawableBottomWidth = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableBottomWidth, 0);
        drawableLeftHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableLeftHeight, 0);
        drawableTopHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableTopHeight, 0);
        drawableRightHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableRightHeight, 0);
        drawableBottomHeight = typedArray.getDimensionPixelSize(R.styleable.TextViewDrawable_drawableBottomHeight, 0);
        isAliganCenter = typedArray.getBoolean(R.styleable.TextViewDrawable_isAliganCenter, true);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        Drawable drawableTop = drawables[1];
        Drawable drawableRight = drawables[2];
        Drawable drawableBottom = drawables[3];
        if (drawableLeft != null) {
            setDrawable(drawableLeft, 0, drawableLeftWidth, drawableLeftHeight);
        }
        if (drawableTop != null) {
            setDrawable(drawableTop, 1, drawableTopWidth, drawableTopHeight);
        }
        if (drawableRight != null) {
            setDrawable(drawableRight, 2, drawableRightWidth, drawableRightHeight);
        }
        if (drawableBottom != null) {
            setDrawable(drawableBottom, 3, drawableBottomWidth, drawableBottomHeight);
        }
        this.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    private void setDrawable(Drawable drawable, int tag, int drawableWidth, int drawableHeight) {
        // 首先要有数据
        if (getLineCount() == 0) {
            return;
        }
        //获取图片实际长宽
        int mDrawableWidth = drawableWidth == 0 ? drawable.getIntrinsicWidth() : drawableWidth;
        int mDrawableHeight = drawableHeight == 0 ? drawable.getIntrinsicHeight() : drawableHeight;
        // view 的高度
        int mViewHeight = getHeight();
        // 行数、行高、行数*行高
        int mLineCount = getLineCount();
        int mLineHeight = getLineHeight();
        int mTextHeight = mLineCount * mLineHeight;
        // 每行的offset
        int mLineOffset = 0;
        if (mViewHeight > mTextHeight) {
            mLineOffset = (mViewHeight - mTextHeight) / mLineCount;
        }
        // 最终行高
        int mFinalLineHeight = mLineHeight + mLineOffset;
        //
        int left = 0, top = 0, right = 0, bottom = 0;
        switch (tag) {
            case 0:
            case 2:
                // 中心点向上移动的 行 (mLineCount - 1) / 2.0f
                // 1行 (1 - 1) / 2.0f) = 0 不移动
                // 2行 (2 - 1) / 2.0f) = 0.5 移动0.5行
                // 3行 (3 - 1) / 2.0f) = 1 移动1行
                int moveTop = -(int) (((mLineCount - 1) / 2.0f) * mFinalLineHeight);
                //
                left = 0;
                top = isAliganCenter ? 0 : moveTop;
                right = mDrawableWidth;
                bottom = top + mDrawableHeight;
                break;
            case 1:
                left = isAliganCenter ? 0 : -mWidth / 2 + mDrawableWidth / 2;
                top = 0;
                right = left + mDrawableWidth;
                bottom = top + mDrawableHeight;
                break;
        }
        drawable.setBounds(left, top, right, bottom);
    }
}
