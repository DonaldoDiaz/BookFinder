package com.donaldo.bookfinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donaldo.bookfinder.bookapi.pojos.Bookfinder;
import com.donaldo.bookfinder.bookapi.pojos.IndustryIdentifier;
import com.donaldo.bookfinder.bookapi.pojos.Item;

import java.util.ArrayList;
import java.util.List;

public class BooksByNameAdapter extends RecyclerView.Adapter<BooksByNameHolder>  {

    private ArrayList<Books> books;
    private int resource;
    private BooksByNameHolder.BooksListener booksListener;
    private Context context;


    public BooksByNameAdapter(ArrayList<Books> books, int resource, Context context /*,BooksByNameHolder.BooksListener booksListener*/) {
        this.books = books;
        this.resource = resource;
        this.context = context;
        //this.booksListener = booksListener;
    }

    @NonNull
    @Override
    public BooksByNameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new BooksByNameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksByNameHolder holder, int position) {
        final Books book = books.get(position);
        holder.setBook(book, context);
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public void updateBookList(List<Item> items){
        for (Item item: items) {

            System.out.println(item.getId());

            String thumbnail = "http://notfound";

            if(item.getVolumeInfo().getImageLinks() != null){
                thumbnail = item.getVolumeInfo().getImageLinks().getThumbnail();
            }

            Books book = new Books(
                    thumbnail,
                    item.getVolumeInfo().getTitle(),
                    loadAuthors(item.getVolumeInfo().getAuthors()),
                    item.getVolumeInfo().getPublisher(),
                    loadISBN(item.getVolumeInfo().getIndustryIdentifiers()),
                    item.getVolumeInfo().getPublishedDate());
            books.add(book);
        }
        notifyDataSetChanged();
    }

    public String loadAuthors(List<String> lstAuthors){
        String authors = "";

        if(lstAuthors != null){
            for (String author: lstAuthors) {
                authors += author;
            }
            return authors;
        }
        return "Undefined Author";
    }

    public String loadISBN(List<IndustryIdentifier> industryIdentifiers){
        if(industryIdentifiers != null){
            for (IndustryIdentifier idf: industryIdentifiers) {
                return idf.getIdentifier();
            }
        }
        return "0";
    }
}
