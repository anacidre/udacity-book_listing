package com.example.android.book_listing;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.android.book_listing.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ana on 14/06/2017.
 */
public class BookAdapter  extends ArrayAdapter<Book>{

    private final static String AUTHOR = "Author: ";
    private final static String PUBLISHER = "Publisher: ";
    private final static String PAGE_COUNT = "Page count: ";
    public BookAdapter (Activity context, ArrayList<Book> books){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0,books);
    }
    private static class ViewHolder{
        ImageView thumbnail;
        TextView bookTitle;
        TextView author;
        TextView publisher;
        TextView pageCount;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View bookList = convertView;
        if (bookList == null){
            bookList = LayoutInflater.from(getContext()).inflate(R.layout.book_listing_sample,parent,false);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView)bookList.findViewById(R.id.thumbnail);
            holder.bookTitle = (TextView) bookList.findViewById(R.id.book_title);
            holder.author = (TextView) bookList.findViewById(R.id.author);
            holder.publisher = (TextView) bookList.findViewById(R.id.publisher);
            holder.pageCount = (TextView) bookList.findViewById(R.id.page_count);
            bookList.setTag(holder);
        }else {
            holder = (ViewHolder)bookList.getTag();
        }
        Book currentBook = getItem(position);
        //we use Picasso Library to convert the url from JSONObject imageLinks to a image(@thumbnail)
        Picasso.with(getContext()).load(currentBook.getThumbnail()).into(holder.thumbnail);

        // We set the new value to the book title that is returned from the HTTP request
        holder.bookTitle.setText(currentBook.getTitle());

        //We set the returned value from the HTTP request
        holder.author.setText(AUTHOR + currentBook.getAuthor());

        holder.publisher.setText(PUBLISHER + currentBook.getPublisher());

        holder.pageCount.setText(PAGE_COUNT + currentBook.getPageCount());

        return bookList;
    }

}

