package com.renyu.administrator.atapplication;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：任宇
 * 日期：2019/11/25 16:17
 * 注释：
 */
public class GridItemDecorationForAllData2Flag extends RecyclerView.ItemDecoration {

    private  int mGridColumn , mGridSpacing;

    public GridItemDecorationForAllData2Flag(int column, int spacing) {
        mGridColumn = column;
        mGridSpacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.set(mGridSpacing, 0, mGridSpacing, mGridSpacing);

        int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition < mGridColumn) {
            outRect.top = mGridSpacing; //顶部两个距离顶部的距离
        }

        if(itemPosition%mGridColumn == 0){
            outRect.left = 2*mGridSpacing ;  //第一列
            outRect.right = mGridSpacing / 2;  //第一列
        }
//        if(itemPosition%mGridColumn == 1){
//            outRect.left = mGridSpacing/2 ;  //第二列
//            outRect.right = mGridSpacing ;  //第二列
//        }

    }

}
