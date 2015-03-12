package zan.jie.lee.rssreader.modules.presenters;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zan.jie.lee.commons.presenter.DetailRssPresenter;
import zan.jie.lee.rssreader.services.ConnectivityService;
import zan.jie.lee.rssreader.ui.activities.RssDetailActivity;

/**
 * Created by jie.lee on 12/3/15.
 */
@Module(library = true, complete = false, injects = RssDetailActivity.class)
public class DetailRssModule {

    @Provides
    @Singleton
    DetailRssPresenter providePresenter(ConnectivityService connectivityService) {
        return new DetailRssPresenter(connectivityService);
    }
}
