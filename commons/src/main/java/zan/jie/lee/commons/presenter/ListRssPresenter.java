package zan.jie.lee.commons.presenter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import zan.jie.lee.commons.db.IDB;
import zan.jie.lee.commons.model.News;
import zan.jie.lee.commons.services.INetClient;
import zan.jie.lee.commons.services.INetService;

/**
 * Created by jie.lee on 6/3/15.
 */
public class ListRssPresenter implements INetClient {

    private static String FEED_URL = "https://www.diagonalperiodico.net/rss/portada.xml";
    private RSSView view;
    private INetService service;
    private IDB<News> newsDAO;

    @Inject
    public ListRssPresenter(INetService service, IDB newsDAO){
        this.service = service;
        this.newsDAO = newsDAO;

    }

    public void loadRss() {
        List<News> newsList = newsDAO.getAll();
        if(newsList != null || !newsList.isEmpty()){
            this.view.setRSSItens(newsList);
        }
        service.loadRss(FEED_URL, this);
    }

    public void setView(RSSView view) {
        if (view == null) {
            throw new IllegalArgumentException("View Can not be null");
        }
        this.view = view;
    }

    public static void sortByDate(final List<News> adapterList) {
        Collections.sort(adapterList, new Comparator<News>() {
            public int compare(final News arg0, final News arg1) {
                return arg0.getDateTime() < arg1.getDateTime() ? +1 : (arg0.getDateTime() > arg1.getDateTime() ? -1 : 0);
            }
        });
    }

    public void onItemSelected(News news) {
        this.view.showItemDetailView(news);
    }

    @Override
    public void onLoadResult(List<News> newsList) {
        newsDAO.clear();
        for(News news : newsList){
            newsDAO.create(news);
        }
        sortByDate(newsList);
        this.view.setRSSItens(newsList);

    }


    public interface RSSView {
        void setRSSItens(List<News> newsList);
        public void showItemDetailView(News news);
    }
}
