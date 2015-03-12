package zan.jie.lee.rssreader.application;

import android.content.Context;

/**
 * Created by jie.lee on 10/3/15.
 */
public enum ContextProvider {
    INSTANCE;

    private static Context context;

    ContextProvider() {
    }

    protected static void setContext(Context context) {
        if (ContextProvider.getContext() == null) {
            ContextProvider.context = context;
        }
    }

    public static Context getContext() {
        return context;
    }
}



