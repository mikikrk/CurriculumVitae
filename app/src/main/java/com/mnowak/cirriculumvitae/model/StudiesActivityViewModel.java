package com.mnowak.cirriculumvitae.model;

import android.content.res.Resources;
import androidx.databinding.ObservableField;
import android.graphics.Typeface;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;

import com.mnowak.cirriculumvitae.R;
import com.mnowak.cirriculumvitae.utils.ResourcesUtils;

import java.io.Serializable;
import java.util.List;

public class StudiesActivityViewModel extends PicturedViewModel {
    public String name;
    public String description;
    public String iconUri;
    public int importantEvents;
    public List<EventViewModel> events;

    public ObservableField<Boolean> expanded = new ObservableField<>(false);

    public void setExpanded(boolean expanded) {
        this.expanded.set(expanded);
    }

    public void toggleExpanded() {
        setExpanded(!expanded.get());
    }

    public class EventViewModel implements Serializable {
        private static final String DIVIDER = " - ";

        public String name;
        public String description;
        public String function;

        private @ColorInt int primaryColor;
        private @ColorInt int regularColor;
        private @ColorInt int redundantColor;

        private Spannable expandedText;
        private Spannable reducedText;

        public void prepare(View view) {
            readColors(view);
            expandedText = getSpannableText(true);
            reducedText = getSpannableText(false);
        }

        private void readColors(View view) {
            Resources resources = view.getResources();
            primaryColor = ResourcesUtils.getColor(resources, R.color.colorTextPrimary);
            regularColor = ResourcesUtils.getColor(resources, R.color.colorTextSecondary);
            redundantColor = ResourcesUtils.getColor(resources, R.color.colorTextRedundant);
        }

        public Spannable getPreparedEventText(boolean expanded) {
            return expanded ? expandedText : reducedText;
        }

        @NonNull
        private Spannable getSpannableText(boolean expanded) {
            String displayedString = buildDisplayedString(expanded);
            Spannable spannable = new SpannableString(displayedString);
            int nameEnd = name.length();
            int descriptionEnd = TextUtils.isEmpty(description) || !expanded ? nameEnd : nameEnd + description.length() + DIVIDER.length();
            setSpans(primaryColor, regularColor, redundantColor, displayedString, spannable, nameEnd, descriptionEnd);
            return spannable;
        }

        private String buildDisplayedString(boolean expanded) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name);
            if (expanded) {
                appendStringAfterPauseIfNotEmpty(stringBuilder, description);
            }
            appendStringAfterPauseIfNotEmpty(stringBuilder, function);
            return stringBuilder.toString();
        }

        private void appendStringAfterPauseIfNotEmpty(StringBuilder stringBuilder, String string) {
            if (!TextUtils.isEmpty(string)) {
                stringBuilder.append(" - ")
                        .append(string);
            }
        }

        private void setSpans(@ColorInt int primaryColor, @ColorInt int regularColor, @ColorInt int redundantColor, String displayedString, Spannable spannable, int nameEnd, int descriptionEnd) {
            spannable.setSpan(new ForegroundColorSpan(primaryColor), 0, nameEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, nameEnd, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(redundantColor), nameEnd, descriptionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(regularColor), descriptionEnd, displayedString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
