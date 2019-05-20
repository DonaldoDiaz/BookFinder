package com.donaldo.bookfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.donaldo.bookfinder.bookapi.ApiUtils;
import com.donaldo.bookfinder.bookapi.SearchBooksService;
import com.donaldo.bookfinder.bookapi.pojos.Bookfinder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BooksByNameHolder.BooksListener{

    SearchBooksService searchBooksService;
    private Button btnSearch;
    private EditText edtBookName;
    private RecyclerView bookRecycler;
    BooksByNameAdapter booksByNameAdapter;
    private ArrayList<Books> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookRecycler = findViewById(R.id.detailOrderRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        bookRecycler.setLayoutManager(linearLayoutManager);

        data = new ArrayList<>(0);
        booksByNameAdapter = new BooksByNameAdapter(data, R.layout.cardview_books, this);
        bookRecycler.setAdapter(booksByNameAdapter);

        searchBooksService = ApiUtils.getBooksByName();

        edtBookName = findViewById(R.id.edtBookName);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(findBook);

    }

    public View.OnClickListener findBook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String bookName = edtBookName.getText().toString().trim();
            if (bookName.length() > 0){
                searchBooksService.getBooksByName(bookName.replace(" ", "+")).enqueue(new Callback<Bookfinder>() {
                    @Override
                    public void onResponse(Call<Bookfinder> call, Response<Bookfinder> response) {
                        if (response.isSuccessful()) {
                            booksByNameAdapter.updateBookList(response.body().getItems());
                        }else{
                            System.out.println("NO SIRVIO");
                        }
                    }

                    @Override
                    public void onFailure(Call<Bookfinder> call, Throwable t) {
                        Log.d("ERROR->", t.toString());
                    }
                });
            }else{
                //booksByNameAdapter.updateBookList();
            }
        }
    };

    @Override
    public void onBooksClicked(Books books) {
        System.out.println("ALOHA " + books.getBook_name());
    }
}
