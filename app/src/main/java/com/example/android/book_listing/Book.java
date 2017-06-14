package com.example.android.book_listing;

/**
 * Created by ana on 14/06/2017.
 */

public class Book {

    private String mThumbnail;
    private String mTitle;
    private StringBuilder mAuthor;
    private String mPublisher;
    private String mPageCount;
    private String mUrl;

    public Book(String thumbnail, String title, StringBuilder author, String publisher, String pageCount, String url) {

        mThumbnail = thumbnail;
        mTitle = title;
        mAuthor = author;
        mPublisher = publisher;
        mPageCount = pageCount;
        mUrl = url;
    }
    public String getThumbnail(){return  mThumbnail;}

    public String getTitle() {
        return mTitle;
    }

    public StringBuilder getAuthor() {
        return mAuthor;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public String getPageCount() {
        return mPageCount;
    }

    public String getUrl(){return mUrl;}
}
