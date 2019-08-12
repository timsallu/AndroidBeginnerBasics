package com.example.androidbeginnerbasics.Util;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalSpacingItemDecorator extends RecyclerView.ItemDecoration {

    /*
    * 1.java class exyend recycle item decoration
    * 2.create spacinf constant
    * 3.insert overide method itemoffset
    * 4.set Rect variable based on need , left right bottom top spacing*/

    private final int verticalspasing;

    public VerticalSpacingItemDecorator(int verticalspasing) {
        this.verticalspasing = verticalspasing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom=verticalspasing;
        outRect.left=15;
        outRect.right=15;
    }
}
