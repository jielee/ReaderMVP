package zan.jie.lee.rssreader.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zan.jie.lee.commons.db.IDB;
import zan.jie.lee.rssreader.db.DBNews;

/**
 * Created by jie.lee on 12/3/15.
 */
@Module(library = true, complete = false)
public class DBModule {

    @Provides
    @Singleton
    IDB provideNetService(@Named(RootModule.APP_CONTEXT) Context context) {
        return new DBNews(context);
    }
}
