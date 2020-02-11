package com.renyu.administrator.atapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * 作者：任宇
 * 日期：2020/2/11 14:38
 * 注释：
 */
public class ViewSpan extends ReplacementSpan {
    protected View view;
    private int maxWidth;

    public ViewSpan(View v, int maxWidth) {
        super();
        this.maxWidth = maxWidth;
        view = v;
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void prepView() {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(maxWidth, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(widthSpec, heightSpec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        prepView();

        canvas.save();
        //Centering the token looks like a better strategy that aligning the bottom
        int padding = (bottom - top - view.getBottom()) / 2;
        canvas.translate(x, bottom - view.getBottom() - padding);
        //这一句是重点，将view画在指定canvas上
        view.draw(canvas);
        canvas.restore();
    }
    @Override
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fm) {
        prepView();

        if (fm != null) {
            //We need to make sure the layout allots enough space for the view
            int height = view.getMeasuredHeight();
            int need = height - (fm.descent - fm.ascent);
            if (need > 0) {
                int ascent = need / 2;
                //This makes sure the text drawing area will be tall enough for the view
                fm.descent += need - ascent;
                fm.ascent -= ascent;
                fm.bottom += need - ascent;
                fm.top -= need / 2;
            }
        }

        return view.getRight();
    }
}
