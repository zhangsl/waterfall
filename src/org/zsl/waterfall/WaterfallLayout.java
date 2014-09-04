package org.zsl.waterfall;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class WaterfallLayout extends ViewGroup {
	private static final String TAG = "WaterfallLayout";
    private int mFallCount = 1;
    private int[] mHeights = null;
    private int mFallWidth = 0;

    public WaterfallLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    public WaterfallLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterfallLayout(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setFallCount(int count) {
        mFallCount = count;
        mHeights = new int[mFallCount];
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        mFallWidth = MeasureSpec.getSize(widthMeasureSpec) / mFallCount;
        int childWidetSpec = MeasureSpec.makeMeasureSpec(mFallWidth, MeasureSpec.EXACTLY);
        int childHeight = 0;
        
        for (int i = 0; i < childCount; i ++) {
            View child = getChildAt(i);
            LayoutParams params = child.getLayoutParams();
            childHeight = params.height;
            int min = getMinHeightIndex();
            mHeights[min] = mHeights[min] + childHeight;
            child.measure(childWidetSpec, heightMeasureSpec);
        }
        
        int max = getMaxHeightIndex();
        setMeasuredDimension(ViewGroup.LayoutParams.MATCH_PARENT, mHeights[max]);
        
        clearHeights();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int childLeft = 0;
        for (int i = 0; i < childCount; i ++) {
            View child = getChildAt(i);
            
            int min = getMinHeightIndex();
            childLeft = mFallWidth * min + l;
            LayoutParams params = child.getLayoutParams();
            int childTop = mHeights[min];
            int childHeight = params.height;
            Log.d(TAG, "onLayout  MeasuredHeight " + childHeight);
            child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + childHeight);
            mHeights[min] = mHeights[min] + childHeight;
            
        }
        
        clearHeights();
    }
    
    private int getMinHeightIndex() {
        int min = 0;
        
        for (int i = 0; i < mFallCount; i ++) {
            if (mHeights[min] > mHeights[i]) {
                min = i;
            }
        }
        
        return min;
    }
    
    private int getMaxHeightIndex() {
        int max = 0;
        
        for (int i = 0; i < mFallCount; i ++) {
            if (mHeights[max] < mHeights[i]) {
                max = i;
            }
        }
        
        return max;
    }
    
    private void clearHeights() {
        for (int i = 0; i < mFallCount; i ++) {
            mHeights[i] = 0;
        }
    }

}
