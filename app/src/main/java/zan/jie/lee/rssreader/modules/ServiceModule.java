package zan.jie.lee.rssreader.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zan.jie.lee.commons.services.INetService;
import zan.jie.lee.rssreader.services.ConnectivityService;
import zan.jie.lee.rssreader.services.NetService;

/**
 * Created by jie.lee on 12/3/15.
 */
@Module(library = true, complete = false)
public class ServiceModule {

    @Provides
    @Singleton
    INetService provideNetService(@Named(RootModule.APP_CONTEXT) Context context) {
        return new NetService(context);
    }

    @Provides
    @Singleton
    ConnectivityService provideConnectivity(@Named(RootModule.APP_CONTEXT) Context context) {
        return new ConnectivityService(context);
    }
}
