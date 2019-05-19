package com.donaldo.bookfinder.bookapi;

import com.donaldo.bookfinder.bookapi.SearchBooksService;

public class ApiUtils {
    public static final String BASE_URL = "https://www.googleapis.com/books/v1/";

    public static SearchBooksService getBooksByName () {
        return NetworkClient.getRetrofitClient(BASE_URL).create(SearchBooksService.class);
    }
}
