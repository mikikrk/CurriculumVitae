package com.example.mnowak.cirriculumvitae.utils;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

public class ResourcesUtils {
    public static @ColorInt int getColor(Resources resources, @ColorRes int colorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return resources.getColor(colorRes, null);
        } else {
            return resources.getColor(colorRes);
        }
    }
}
