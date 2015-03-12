package zan.jie.lee.rssreader.application;

import android.app.Application;

import java.util.List;

import dagger.ObjectGraph;
import zan.jie.lee.rssreader.modules.DBModule;
import zan.jie.lee.rssreader.modules.RootModule;
import zan.jie.lee.rssreader.modules.ServiceModule;

/**
 * Created by jie.lee on 10/3/15.
 */
public class ReaderApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules());

    }

    public ObjectGraph plusModules(List<Object> modules) {
        return objectGraph.plus(modules.toArray());
    }

    private Object[] getModules() {
        Object[] modules = new Object[]{new RootModule(this), new ServiceModule(), new DBModule()};
        return modules;
    }
}
