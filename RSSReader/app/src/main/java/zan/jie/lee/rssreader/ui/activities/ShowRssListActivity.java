package zan.jie.lee.rssreader.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import zan.jie.lee.commons.model.News;
import zan.jie.lee.commons.presenter.ListRssPresenter;
import zan.jie.lee.commons.services.INetService;
import zan.jie.lee.rssreader.R;
import zan.jie.lee.rssreader.db.DBNews;
import zan.jie.lee.rssreader.modules.presenters.ListRssModule;
import zan.jie.lee.rssreader.services.ConnectivityService;
import zan.jie.lee.rssreader.services.NetService;
import zan.jie.lee.rssreader.ui.adapters.RSSItemAdapter;


public class ShowRssListActivity extends BaseActivity implements ListRssPresenter.RSSView {


    @Inject
    ListRssPresenter rssPresenter;

    private RSSItemAdapter rssItemAdapter;

    private ProgressDialog progressDialog;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.reader_list_news)
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setTitle("Reader");
        setSupportActionBar(toolbar);
        this.progressDialog = ProgressDialog.show(this, null, "CARGANDO");

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_show_rss_list;
    }

    @Override
    protected void initLayout() {
        ButterKnife.inject(this);
        this.rssPresenter.setView(this);

    }

    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected List<Object> getModules() {
        return new ArrayList<Object>() {{
            add(new ListRssModule());
        }};
    }

    @Override
    protected boolean hasInjection() {
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_rss_list, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        rssPresenter.loadRss();
    }

    @Override
    public void setRSSItens(List<News> newsList) {
        if(this.progressDialog!=null){
            this.progressDialog.dismiss();
        }
        rssItemAdapter = new RSSItemAdapter(this, R.layout.rss_adapter, newsList, rssPresenter);
        listView.setAdapter(rssItemAdapter);
        listView.setOnItemClickListener(rssItemAdapter);
    }

    @Override
    public void showItemDetailView(News news) {
        Intent i = new Intent(this, RssDetailActivity.class);
        i.putExtra("news", news);
        startActivity(i);
    }
}
