package zan.jie.lee.rssreader.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import zan.jie.lee.commons.model.News;
import zan.jie.lee.commons.presenter.DetailRssPresenter;
import zan.jie.lee.rssreader.R;
import zan.jie.lee.rssreader.modules.presenters.DetailRssModule;


public class RssDetailActivity extends BaseActivity implements DetailRssPresenter.DetailView {

    @InjectView(R.id.title)
    TextView title;

    @InjectView(R.id.description)
    TextView description;

    @InjectView(R.id.image)
    ImageView image;

    @InjectView(R.id.linkButton)
    TextView link;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DetailRssPresenter detailPresenter;


    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);

        description.setEllipsize(TruncateAt.END);
        image.setVisibility(View.VISIBLE);

        toolbar.setTitle("Reader");
        setSupportActionBar(toolbar);

        Intent i = getIntent();

        if (i != null) {
            News news = (News) i.getSerializableExtra("news");
            title.setText(Html.fromHtml(news.getTitle()));
            if (news.getImageUrl() == null || news.getImageUrl().isEmpty()) {
                findViewById(R.id.imageContainer).setVisibility(View.GONE);
            } else {
                Picasso.with(this)
                        .load(news.getImageUrl())
                        .error(R.drawable.placeholder)
                        .into(image);
            }
            String content = news.getContent() == null ? news.getDescription() : news.getContent();
            description.setText(Html.fromHtml(content));
            link.setVisibility(View.VISIBLE);
            url = news.getNewsUrl();

        }
    }

    @OnClick(R.id.linkButton)
    void onLinkButton() {
        detailPresenter.getLink(url);
        Log.d("URL", url);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initLayout() {
        this.detailPresenter.setView(this);
    }

    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected List<Object> getModules() {
        return new ArrayList<Object>() {{
            add(new DetailRssModule());
        }};
    }

    @Override
    protected boolean hasInjection() {
        return true;
    }

    @Override
    public void showLink(String url) {
        if (detailPresenter.verifyConnectivity()) {
            try {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_rss_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
