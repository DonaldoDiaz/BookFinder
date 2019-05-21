package com.donaldo.bookfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity {

    String book_name;
    String book_description;
    String book_url_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        book_name = b.getString("book_name");
        book_description = b.getString("book_description");
        book_url_buy = b.getString("book_url_buy");

        System.out.println(book_description);

        TextView bookDescription = findViewById(R.id.bookDescription);
        bookDescription.setText(book_description);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(book_name);

        FloatingActionButton fab = findViewById(R.id.buyBook);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (book_url_buy!= null){
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(book_url_buy)));
                    }else{
                        Snackbar.make(view, "No link para compra disponible", Snackbar.LENGTH_LONG).show();
                    }
                    //Snackbar.make(view, "Link: "+ book_url_buy, Snackbar.LENGTH_LONG).show();
                    //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(book_url_buy)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    Snackbar.make(view, "Error al intentar abrir el link", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
