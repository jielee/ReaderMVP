package zan.jie.lee.rssreader.modules;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import zan.jie.lee.rssreader.application.ReaderApplication;

/**
 * Created by jie.lee on 12/3/15.
 */
@Module(injects = ReaderApplication.class, library = true, complete = false)
public class RootModule {

    public static final String APP_CONTEXT = "context";

    private Context context;

    public RootModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named(RootModule.APP_CONTEXT)
    Context provideAppContext() {
        return this.context;
    }
}
