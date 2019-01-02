package com.mnowak.cirriculumvitae.widgets

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.mnowak.cirriculumvitae.R

class SkillTextView @JvmOverloads constructor(

        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0

) : AppCompatTextView(context, attributeSet, defStyleAttr) {

    private val horizontalPadding = resources.getDimensionPixelSize(R.dimen.skill_item_horizontal_padding)
    private val verticalPadding = resources.getDimensionPixelSize(R.dimen.skill_item_vertical_padding)
    private val horizontalGap = resources.getDimensionPixelSize(R.dimen.skill_items_horizontal_gap)
    private val textSize = resources.getDimensionPixelSize(R.dimen.more_info_regular_text_size)
    private val backgroundDrawable = resources.getDrawable(R.drawable.genre_background, null)
    private val whiteColor = ContextCompat.getColor(context, android.R.color.white)

    var onAttachedToWindow: (() -> Unit)? = null
    var onDetachedFromWindow: (() -> Unit)? = null

    init {
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        layoutParams.marginStart = horizontalGap / 2
        layoutParams.marginEnd = horizontalGap / 2
        setLayoutParams(layoutParams)
        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        includeFontPadding = true
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        setTextColor(whiteColor)
        background = backgroundDrawable
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        maxLines = 1
        measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    fun setColor(levelColor: LevelColor) {
            val backgroundColor = ContextCompat.getColor(context, levelColor.color)
            backgroundDrawable.mutate().setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        onAttachedToWindow?.invoke()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        onDetachedFromWindow?.invoke()
    }

    enum class LevelColor(
            @ColorRes val color: Int
    ) {
        GOOD(R.color.colorGoodKnowledge),
        MEDIUM(R.color.colorMediumKnowledge),
        LOW(R.color.colorLowKnowledge)
    }
}
