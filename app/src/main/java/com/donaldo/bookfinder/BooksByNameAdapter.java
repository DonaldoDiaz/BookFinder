package com.donaldo.bookfinder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donaldo.bookfinder.bookapi.pojos.IndustryIdentifier;
import com.donaldo.bookfinder.bookapi.pojos.Item;

import java.util.ArrayList;
import java.util.List;

public class BooksByNameAdapter extends RecyclerView.Adapter<BooksByNameHolder>  {

    private ArrayList<Books> books;
    private int resource;
    private BooksByNameHolder.BooksListener booksListener;


    public BooksByNameAdapter(ArrayList<Books> books, int resource, BooksByNameHolder.BooksListener booksListener) {
        this.books = books;
        this.resource = resource;
        this.booksListener = booksListener;
    }

    @NonNull
    @Override
    public BooksByNameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
        return new BooksByNameHolder(view, booksListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksByNameHolder holder, int position) {
        final Books book = books.get(position);
        holder.setBook(book);
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public void updateBookList(List<Item> items){
        books.clear();
        for (Item item: items) {

            System.out.println(item.getId());

            String thumbnail = "http://notfound";

            if(item.getVolumeInfo().getImageLinks() != null){
                thumbnail = item.getVolumeInfo().getImageLinks().getThumbnail();
            }

            Books book = new Books(
                    item.getId(),
                    thumbnail,
                    item.getVolumeInfo().getDescription(),
                    item.getVolumeInfo().getTitle(),
                    loadAuthors(item.getVolumeInfo().getAuthors()),
                    item.getVolumeInfo().getPublisher(),
                    loadISBN(item.getVolumeInfo().getIndustryIdentifiers()),
                    item.getVolumeInfo().getPublishedDate(),
                    item.getSaleInfo().getBuyLink());
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
