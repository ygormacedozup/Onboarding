package br.com.zup.onboarding.android;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    private static Typeface font;

    private Utils() {
    }

    public static Typeface getFont(Context context) {
        if (font == null) {
            font = Typeface.createFromAsset(context.getAssets(), Constants.FONT_PATH);
        }

        return font;
    }
}
