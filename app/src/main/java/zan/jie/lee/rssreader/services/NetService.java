package zan.jie.lee.rssreader.services;

import android.content.Context;
import android.util.Log;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import zan.jie.lee.commons.helper.RSSHelper;
import zan.jie.lee.commons.model.News;
import zan.jie.lee.commons.services.INetClient;
import zan.jie.lee.commons.services.INetService;

/**
 * Created by jie.lee on 10/3/15.
 */
public class NetService implements INetService {

    Context context;

    @Inject
    public NetService(Context context) {
        this.context = context;
    }

    public void loadRss(String url, final INetClient client) {
        Ion.with(context).load(url).asInputStream().setCallback(new FutureCallback<InputStream>() {
            List<News> newsList;

            @Override
            public void onCompleted(Exception e, InputStream result) {
                if (e != null) {
                    Log.e("ERROR", e.getMessage());
                }
                newsList = RSSHelper.loadRss(result, "utf8");
                client.onLoadResult(newsList);
            }

        });
    }

}
