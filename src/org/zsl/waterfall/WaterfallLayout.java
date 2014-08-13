package org.zsl.waterfall;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class WaterfallLayout extends ViewGroup {
	private static final String TAG = "WaterfallLayout";
    private int mFallCount = 1;
    private int[] mHeights = null;

    public WaterfallLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    public void setFallCount(int count) {
        mFallCount = count;
        mHeights = new int[mFallCount];
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	Log.d(TAG, "onMeasure");
        int childCount = getChildCount();
        int childWidth = MeasureSpec.getSize(widthMeasureSpec) / mFallCount;
        int childWidetSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
        
        
        for (int i = 0; i < childCount; i ++) {
            View child = getChildAt(i);
            int min = getMinHeightIndex();
            mHeights[min] = mHeights[min] + child.getMeasuredHeight();
            child.measure(childWidetSpec, heightMeasureSpec);
        }
        
        int max = getMaxHeightIndex();
        setMeasuredDimension(ViewGroup.LayoutParams.MATCH_PARENT, mHeights[max]);
        
        clearHeights();
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//    	super.onl
        int childCount = getChildCount();
        Log.d(TAG, "onLayout  left: " + l + "   top: " + t + "   r: " + r + "  b: " + b);
        for (int i = 0; i < childCount; i ++) {
            View child = getChildAt(i);
            int min = getMinHeightIndex();
            mHeights[min] = mHeights[min] + child.getMeasuredHeight();
            int childLeft = (r - l) / mFallCount * min;
            int childTop = mHeights[min];
            int childHeight = child.getMeasuredHeight();
            child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + childHeight);
            Log.d(TAG, "onLayout  left: " + childLeft + "   top: " + childTop + "   r: "
            + childLeft + child.getMeasuredWidth() + "  b: " + childTop + childHeight);
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
