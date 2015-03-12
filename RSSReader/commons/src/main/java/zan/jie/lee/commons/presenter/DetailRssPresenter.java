package zan.jie.lee.commons.presenter;

import zan.jie.lee.commons.services.IConnectivityService;

/**
 * Created by jie.lee on 9/3/15.
 */
public class DetailRssPresenter {

    private DetailView view;
    IConnectivityService connectivityService;

    public DetailRssPresenter(IConnectivityService service){
        this.connectivityService = service;
    }

    public void getLink(String url) {
        view.showLink(url);

    }

    public boolean verifyConnectivity(){
        return this.connectivityService.verifyConnectivity();
    }

    public void setView(DetailView view) {
        if (view == null) {
            throw new IllegalArgumentException("View Can not be null");
        }
        this.view = view;
    }

    public interface DetailView {

        void showLink(String url);
    }


}
