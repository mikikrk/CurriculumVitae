package com.example.mnowak.cirriculumvitae.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mnowak.cirriculumvitae.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@BindingMethods({@BindingMethod(type = IconedTextView.class, attribute = "android:text", method = "setText")})
public class IconedTextView extends RelativeLayout {

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvLabel)
    TextView tvLabel;

    public IconedTextView(Context context) {
        super(context);
        init(context, null);
    }

    public IconedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public IconedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public IconedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_iconed_text_view, this, true);
        ButterKnife.bind(this);

        useAttrs(context, attrs);
    }

    private void useAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            setIconView(context, attrs);
            setTextSize(context, attrs);
            setTextColor(context, attrs);
        }
    }

    private void setIconView(Context context, AttributeSet attrs) {
        @StyleableRes int[] iconSrcAttr = new int[] {android.R.attr.src};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, iconSrcAttr);
        setDrawable(typedArray);
        typedArray.recycle();
    }

    private void setDrawable(TypedArray typedArray) {
        int srcIndex = 0;
        Drawable drawable = typedArray.getDrawable(srcIndex);
        if (drawable != null) {
            ivIcon.setImageDrawable(drawable);
        }
    }

    public void setText(String text) {
        tvLabel.setText(text);
    }

    private void setTextSize(Context context, AttributeSet attrs) {
        @StyleableRes int[] textSizeAttr = new int[] {android.R.attr.textSize};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, textSizeAttr);
        int textSize = typedArray.getDimensionPixelSize(0, 0);
        if (textSize > 0) {
            tvLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        typedArray.recycle();
    }

    private void setTextColor(Context context, AttributeSet attrs) {
        @StyleableRes int[] textColorAttr = new int[] {android.R.attr.textColor};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, textColorAttr);
        int textColor = typedArray.getColor(0, 0);
        if (textColor > 0) {
            tvLabel.setTextColor(textColor);
        }
        typedArray.recycle();
    }

}
