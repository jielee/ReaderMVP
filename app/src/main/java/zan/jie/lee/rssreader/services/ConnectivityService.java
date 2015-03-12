package zan.jie.lee.rssreader.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

import zan.jie.lee.commons.services.IConnectivityService;

/**
 * Created by jie.lee on 10/3/15.
 */
public class ConnectivityService implements IConnectivityService {


    private Context context;

    @Inject
    public ConnectivityService(Context context) {
        this.context = context;
    }


    @Override
    public boolean verifyConnectivity() {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isAvailable()
                && networkInfo.isConnected();
    }
}
