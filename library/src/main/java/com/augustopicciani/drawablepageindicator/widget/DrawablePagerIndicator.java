/*
 * Copyright (C) 2015 Augusto Picciani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.augustopicciani.drawablepageindicator.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.augustopicciani.drawablepageindicator.R;
import com.augustopicciani.drawablepageindicator.interfaces.PageIndicator;


/**
 * @author Augusto 27/10/2015
 */

public class DrawablePagerIndicator extends View implements PageIndicator {
    private int imageSpacing;
    private int drawableDefaultId;
    private int drawableSelectedId;
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;
    private Context mContext;
    private int mCurrentPage = 0;
    private boolean mCentered;



    public DrawablePagerIndicator(Context context) {

        super(context);
        this.mContext = context;
    }



    public DrawablePagerIndicator(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DrawablePagerIndicator, 0, 0);
        drawableSelectedId = a.getResourceId(R.styleable.DrawablePagerIndicator_drawableSelected, R.drawable.ic_slider_on);
        drawableDefaultId = a.getResourceId(R.styleable.DrawablePagerIndicator_drawableDefault, R.drawable.ic_slider_off);
        imageSpacing = a.getInteger(R.styleable.DrawablePagerIndicator_imageSpacing, 0);
        this.mCentered = a.getBoolean(R.styleable.DrawablePagerIndicator_centered, false);
        a.recycle();

    }



    public DrawablePagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DrawablePagerIndicator, defStyleAttr, 0);
        drawableSelectedId = a.getResourceId(R.styleable.DrawablePagerIndicator_drawableSelected, R.drawable.ic_slider_on);
        drawableDefaultId = a.getResourceId(R.styleable.DrawablePagerIndicator_drawableDefault, R.drawable.ic_slider_off);
        imageSpacing = a.getInteger(R.styleable.DrawablePagerIndicator_imageSpacing, 0);
        this.mCentered = a.getBoolean(R.styleable.DrawablePagerIndicator_centered, false);

        a.recycle();

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawablePagerIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DrawablePagerIndicator, defStyleAttr, 0);
        drawableSelectedId = a.getResourceId(R.styleable.DrawablePagerIndicator_drawableSelected, R.drawable.ic_slider_on);
        drawableDefaultId = a.getResourceId(R.styleable.DrawablePagerIndicator_drawableDefault, R.drawable.ic_slider_off);
        imageSpacing = a.getInteger(R.styleable.DrawablePagerIndicator_imageSpacing, 0);
        a.recycle();
    }



    /**
     * Specify drawable to use when no selected
     *
     * @param drawableDefaultId
     */
    public void setDrawableDefaultId(int drawableDefaultId) {

        this.drawableDefaultId = drawableDefaultId;
    }



    /**
     * Specify drawable to use when selected
     *
     * @param drawableSelectedId
     */
    public void setDrawableSelectedId(int drawableSelectedId) {

        this.drawableSelectedId = drawableSelectedId;
    }



    /**
     * Specify if should be centered or not
     *
     * @param centered
     */
    public void setCentered(boolean centered) {

        this.mCentered = centered;
    }



    /**
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        if (mViewPager == null) {
            return;
        }
        final int count = mViewPager.getAdapter().getCount();
        if (count == 0) {
            return;
        }

        if (mCurrentPage >= count) {
            setCurrentItem(count - 1);
            return;
        }
        float dx = -1;
        Bitmap imageChildDefault = BitmapFactory.decodeResource(mContext.getResources(), drawableDefaultId);
        Bitmap imageChildSelected = BitmapFactory.decodeResource(mContext.getResources(), drawableSelectedId);
        Bitmap imageToDraw;
        int imageSize = imageChildDefault.getWidth();
        int canvasWidth = canvas.getWidth();
        int circlesWith = (imageSize * count) + imageSpacing * (count - 1);
        int startingPoint = (canvasWidth - circlesWith) / 2;
        for (int i = 0; i < count; i++) {

            if (i == 0) {
                dx = 0;
            }
/**
 * position images indicator on the center
 */
            if (mCentered) {
                dx = startingPoint + i * (imageSize + imageSpacing);
            }

            if (mCurrentPage == i) {
                imageToDraw = imageChildSelected;

            } else {
                imageToDraw = imageChildDefault;
            }
            canvas.drawBitmap(imageToDraw, dx, 0, null);

/**
 * position images indicator from the left
 */
            if (!mCentered) {
                dx += imageChildDefault.getWidth() + imageSpacing;
            }
        }
    }



    /**
     * @param view
     */

    @Override
    public void setViewPager(ViewPager view) {

        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        if (view.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = view;
        mViewPager.setOnPageChangeListener(this);
        invalidate();
    }



    /**
     * @param view
     * @param initialPosition
     */
    @Override
    public void setViewPager(ViewPager view, int initialPosition) {

        setViewPager(view);
        setCurrentItem(initialPosition);
    }



    /**
     * @param item
     */
    @Override
    public void setCurrentItem(int item) {

        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mViewPager.setCurrentItem(item);
        mCurrentPage = item;
        invalidate();
    }



    @Override
    public void notifyDataSetChanged() {

        invalidate();
    }



    /**
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {


        if (mListener != null) {
            mListener.onPageScrollStateChanged(state);
        }
    }



    /**
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        mCurrentPage = position;
        invalidate();

        if (mListener != null) {
            mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }



    /**
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        if (mListener != null) {
            mListener.onPageSelected(position);
        }
    }



    /**
     * @param listener
     */
    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

        mListener = listener;
    }
}
