package zan.jie.lee.rssreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import zan.jie.lee.commons.model.News;


public class DBNews extends DB<News> {

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String CONTENT = "content";
    private static final String IMAGE_URL = "imageUrl";
    private static final String DATE = "date";
    private static final String LINK_URL = "url";
    private static final String DB_NAME = "news";
    private static final String
            DB_CREATE = "create table " + DB_NAME +" (" +
            ID_COL +" integer primary key autoincrement, " +
            TITLE + " text, " +
            DESCRIPTION + " text, " +
            CONTENT + " text, " +
            IMAGE_URL + " text, " +
            LINK_URL + " text, " +
            DATE +" integer)";

    public DBNews(Context context) {
        super(context);
    }

    @Override
    public void create(News news) {
        ContentValues values = new ContentValues();
        values.put(TITLE, news.getTitle());
        values.put(DESCRIPTION, news.getDescription());
        values.put(IMAGE_URL, news.getImageUrl());
        values.put(DATE, news.getDateTime());
        values.put(LINK_URL, news.getNewsUrl());
        values.put(CONTENT, news.getContent());
        insert(values);
    }

    @Override
    public News getFromCursor(Cursor cursor) {
        News news = new News();
        news.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        news.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        news.setImageUrl(cursor.getString(cursor.getColumnIndex(IMAGE_URL)));
        news.setDateTime(cursor.getLong(cursor.getColumnIndex(DATE)));
        news.setNewsUrl(cursor.getString(cursor.getColumnIndex(LINK_URL)));
        news.setContent(cursor.getString(cursor.getColumnIndex(CONTENT)));
        return news;
    }

    @Override
    public String[] getAllColums() {
        String[] allColumns = {"id", TITLE, DESCRIPTION, CONTENT, IMAGE_URL, DATE, LINK_URL};
        return allColumns;
    }

    @Override
    public String getTableName() {
        return DB_NAME;
    }

    @Override
    public String getDbCreate() {
        return DB_CREATE;
    }

    @Override
    public void clear() {
        db().delete(getTableName(), null, null);
    }


}