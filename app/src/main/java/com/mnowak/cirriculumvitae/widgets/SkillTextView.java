package com.mnowak.cirriculumvitae.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mnowak.cirriculumvitae.R;
import com.mnowak.cirriculumvitae.utils.ResourcesUtils;

public class SkillTextView extends AppCompatTextView {

    private @Dimension int horizontalPadding;
    private @Dimension int verticalPadding;
    private @Dimension int textSize;
    private @Dimension int horizontalGap;

    private Drawable backgroundDrawable;
    private @ColorInt int backgroundColor;
    private @ColorInt int whiteColor;

    public SkillTextView(Context context, LevelColor levelColor, String text) {
        super(context);
        init(context, levelColor, text);
    }

    private void init(Context context, LevelColor levelColor, String text) {
        readResources(context, levelColor);
        setUp(text);
    }

    private void readResources(Context context, LevelColor levelColor) {
        Resources resources = context.getResources();
        horizontalPadding = resources.getDimensionPixelSize(R.dimen.skill_item_horizontal_padding);
        verticalPadding = resources.getDimensionPixelSize(R.dimen.skill_item_vertical_padding);
        horizontalGap = resources.getDimensionPixelSize(R.dimen.skill_items_horizontal_gap);
        textSize = resources.getDimensionPixelSize(R.dimen.more_info_regular_text_size);
        backgroundDrawable = resources.getDrawable(R.drawable.genre_background, null);
        backgroundColor = ResourcesUtils.getColor(resources, levelColor.getColor());
        backgroundDrawable.mutate().setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY);
        whiteColor = ResourcesUtils.getColor(resources, android.R.color.white);
    }

    private void setUp(String text) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.setMarginStart(horizontalGap / 2);
        layoutParams.setMarginEnd(horizontalGap / 2);
        setLayoutParams(layoutParams);
        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        setIncludeFontPadding(true);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        setText(text);
        setTextColor(whiteColor);
        setBackground(backgroundDrawable);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setMaxLines(1);
        measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public enum LevelColor {
        GOOD(R.color.colorGoodKnowledge), MEDIUM(R.color.colorMediumKnowledge), LOW(R.color.colorLowKnowledge);

        private @ColorRes int color;

        LevelColor(int color) {
            this.color = color;
        }

        public @ColorRes int getColor() {
            return color;
        }
    }
}
