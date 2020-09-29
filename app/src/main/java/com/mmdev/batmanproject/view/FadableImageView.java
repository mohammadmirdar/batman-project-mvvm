package com.mmdev.batmanproject.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FadableImageView extends androidx.appcompat.widget.AppCompatImageView {

    private FadeSide fadeSide;
    private Context context;

    public FadableImageView(Context context) {
        super(context);

        this.context = context;
        init();
    }

    public FadableImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init();
    }

    public FadableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        init();
    }

    public enum FadeSide{
        BOTTOM,TOP
    }

    private void init(){

        this.setVerticalFadingEdgeEnabled(true);

        this.setEdgeLength(15);

        this.setFadeDirection(FadeSide.BOTTOM);

    }

    public void setFadeDirection(FadeSide side){
        this.fadeSide = side;
    }

    public void setEdgeLength(int length){
        this.setFadingEdgeLength(getPixels(length));
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        return fadeSide.equals(FadeSide.BOTTOM) ? 1.0f : 0.0f;
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        return fadeSide.equals(FadeSide.TOP) ? 1.0f : 0.0f;
    }

    @Override
    public boolean hasOverlappingRendering() {
        return true;
    }

    @Override
    protected boolean onSetAlpha(int alpha) {
        return false;
    }

    private int getPixels(int dipValue) {
        Resources r = context.getResources();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue, r.getDisplayMetrics());
    }
}

