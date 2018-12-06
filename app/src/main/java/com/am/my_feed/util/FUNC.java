package com.am.my_feed.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.am.my_feed.R;
import com.am.my_feed.article.Article;

import static com.am.my_feed.util.CONST.EXTRA_ARTICLE;

public abstract class FUNC {
    public static void openUrlInChromeCustomTab(Context context, Article article, String url) {
        if (!(url.startsWith("http:") || url.startsWith("https:"))) {
            url = "http://" + url;
        }
        Bitmap bookmarkIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_toolbar_bookmark);
        Bitmap shareIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_share);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "http://www.codepath.com");
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ARTICLE, article);
        intent.putExtras(bundle);
        int requestCode = 100;



        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorAccent))
                .addDefaultShareMenuItem()
                .setActionButton(bookmarkIconBitmap, "Share Link", pendingIntent, true)
                .addToolbarItem(1, shareIconBitmap, "Share Link", pendingIntent)
                .enableUrlBarHiding()
                .setShowTitle(true)
                .build();

        customTabsIntent.launchUrl(context, Uri.parse(url));

    }
}
