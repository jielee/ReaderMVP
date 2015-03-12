package zan.jie.lee.rssreader.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.List;

import dagger.ObjectGraph;
import zan.jie.lee.rssreader.application.ReaderApplication;
import zan.jie.lee.rssreader.modules.ActivityModule;

public abstract class BaseActivity extends ActionBarActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private ObjectGraph objectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        getExtras();
        objectGraph = create(getModules());
        initLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objectGraph = null;
    }

    public ObjectGraph plusModules(Object[] modules) {
        return objectGraph.plus(modules);
    }

    protected ObjectGraph create(List<Object> modules) {
        ReaderApplication app = (ReaderApplication) getApplication();
        modules.add(new ActivityModule(this));
        objectGraph = app.plusModules(modules);
        if (hasInjection()) {
            objectGraph.inject(this);
        }
        return objectGraph;
    }

    protected void setLayout() {
        setContentView(getContentView());
    }


    protected abstract int getContentView();

    protected abstract void initLayout();

    protected abstract boolean isFullScreen();

    // Convenience to give child classes room fro extras processing
    protected void getExtras() {
    }

    protected abstract List<Object> getModules();

    protected abstract boolean hasInjection();

}
