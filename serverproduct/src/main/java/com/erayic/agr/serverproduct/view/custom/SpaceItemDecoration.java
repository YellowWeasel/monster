package com.erayic.agr.serverproduct.view.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wxk on 2017/5/19.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    int topSpace;
    public SpaceItemDecoration(int mTopSpace) {
        topSpace=mTopSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view)==0){
            outRect.top=30;
            return;
        }
        outRect.top=topSpace;
    }
}
