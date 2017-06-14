package com.example.android.book_listing;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.booklisting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ana on 14/06/2017.
 */

public class BookResults extends AppCompatActivity {
    private ListView listView;
    private ProgressBar progressBar;
    private BookAdapter mAdapter;
    private TextView noInternet;
    private static final String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Result");
        setContentView(R.layout.list_view);
        //We get the user input text
        String bookTitle = getIntent().getStringExtra("TITLE");
        //We set the maximum results to 5 books
        String end = "&maxResults=20";
        Log.e("TITLE OF THE BOOK IS: ",bookTitle);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        noInternet = (TextView)findViewById(R.id.no_internet);
        //This method check if there is internet connection
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        //We create the new requested url
        String newUrl = BOOK_REQUEST_URL + bookTitle.toLowerCase() + end;
        Log.e("The new URL",newUrl);
        listView = (ListView) findViewById(R.id.list);

        BookAsyncTask task = new BookAsyncTask();
        //If there is internet connection we execute the task
        if (isConnected) {
            task.execute(newUrl );
        }
        else {
            progressBar.setVisibility(View.GONE);
            noInternet.setText(R.string.no_internet_connection);
        }
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(mAdapter);
        // OnItemClickListener will open the website for the specific place
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book book = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(book.getUrl()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Book> result =  Utils.fetchBookList(urls[0]);
            return result;
        }

        /**
         * This method is invoked on the main UI thread after the background work has been
         * completed.
         * <p>
         * It IS okay to modify the UI within this method. We take the {@link Book} object
         * (which was returned from the doInBackground() method) and update the views on the screen.
         */
        protected void onPostExecute(List<Book> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();
            progressBar.setVisibility(View.GONE);

            // If there is a valid list of {@link Book}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
                // If there is no information on a given book we set a text message to inform the user about it
            }else{noInternet.setText(R.string.no_data_found);}
        }
    }
}
