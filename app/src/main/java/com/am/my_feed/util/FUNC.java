package com.am.my_feed.util;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

public abstract class FUNC {
    public static void luanchChromeCustomTaps(Context context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
