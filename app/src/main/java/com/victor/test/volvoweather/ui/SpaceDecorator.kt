package com.victor.test.volvoweather.ui

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View



class SpaceDecorator(val space:Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.left = space/2
        outRect?.right = space/2
        outRect?.top = space/2
        outRect?.bottom = space/2
    }
}