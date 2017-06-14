package com.example.android.book_listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.booklisting.R;

import static com.example.android.booklisting.R.id.search_title;

public class BookActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private String bookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.search_button);
        editText = (EditText) findViewById(search_title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookTitle = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(),BookResults.class);
                intent.putExtra("TITLE",bookTitle);
                startActivity(intent);
            }
        });

        EditText editText = (EditText) findViewById(R.id.search_title);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {

                    return true;
                }
                return false;
            }
        });
    }



}
