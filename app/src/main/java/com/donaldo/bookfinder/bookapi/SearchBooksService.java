package com.donaldo.bookfinder.bookapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.donaldo.bookfinder.bookapi.pojos.Bookfinder;

public interface SearchBooksService {
    @GET("volumes")
    Call<Bookfinder> getBooksByName(@Query("q") String bookName);
}
