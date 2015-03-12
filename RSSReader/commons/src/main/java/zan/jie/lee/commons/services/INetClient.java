package zan.jie.lee.commons.services;

import java.util.List;

import zan.jie.lee.commons.model.News;

/**
 * Created by jie.lee on 10/3/15.
 */
public interface INetClient {

    void onLoadResult(List<News> newsList);
}
