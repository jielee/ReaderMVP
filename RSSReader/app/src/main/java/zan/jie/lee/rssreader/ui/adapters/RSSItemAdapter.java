package zan.jie.lee.rssreader.ui.adapters;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zan.jie.lee.commons.model.News;
import zan.jie.lee.commons.presenter.ListRssPresenter;
import zan.jie.lee.rssreader.R;


/**
 * Created by jie.lee on 4/3/15.
 */
public class RSSItemAdapter extends ArrayAdapter<News> implements AdapterView.OnItemClickListener {

    private ListRssPresenter presenter;

    public RSSItemAdapter(Activity activity, int resource, List<News> newsList, ListRssPresenter presenter) {
        super(activity, resource, newsList);
        this.presenter = presenter;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_adapter, null);
        }
        News item = this.getItem(position);
        if (item != null) {

            TextView itemTitle = (TextView) convertView.findViewById(R.id.title);

            RelativeLayout imageContainer = (RelativeLayout) convertView.findViewById(R.id.imageContainer);
            ProgressBar imageProgressBar = (ProgressBar) convertView.findViewById(R.id.loader);
            ImageView itemImage = (ImageView) convertView.findViewById(R.id.image);
            TextView descrition = (TextView) convertView.findViewById(R.id.description);


            itemImage.setImageBitmap(null);
            if (item.getTitle().trim().equals("")) {
                itemTitle.setVisibility(View.GONE);
            } else {
                itemTitle.setVisibility(View.VISIBLE);
                itemTitle.setText(Html.fromHtml(item.getTitle().trim()));
            }

            imageContainer.setVisibility(View.VISIBLE);

            if (item.getImageUrl() == null || item.getImageUrl().equalsIgnoreCase("")) {
                imageContainer.setVisibility(View.GONE);
                itemTitle.setPadding(20, 0, 0, 0);
                descrition.setPadding(20, 0, 0, 0);
            } else {
                imageProgressBar.setVisibility(View.VISIBLE);
                Picasso.with(this.getContext())
                        .load(item.getImageUrl())
                        .error(R.drawable.placeholder)
                        .into(itemImage);

            }
            descrition.setText(Html.fromHtml(item.getDescription().trim()));
        }

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = this.getItem(position);
        presenter.onItemSelected(news);
    }
}
