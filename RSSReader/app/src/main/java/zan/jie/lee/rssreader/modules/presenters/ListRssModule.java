package zan.jie.lee.rssreader.modules.presenters;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zan.jie.lee.commons.db.IDB;
import zan.jie.lee.commons.presenter.ListRssPresenter;
import zan.jie.lee.commons.services.INetService;
import zan.jie.lee.rssreader.ui.activities.ShowRssListActivity;

/**
 * Created by jie.lee on 12/3/15.
 */
@Module(library = true, complete = false, injects = ShowRssListActivity.class)
public class ListRssModule {


    @Provides
    @Singleton
    ListRssPresenter providePresenter(INetService service, IDB dbNews) {
        return new ListRssPresenter(service, dbNews);
    }
}
