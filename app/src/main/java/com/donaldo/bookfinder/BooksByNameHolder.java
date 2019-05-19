package com.donaldo.bookfinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

class BooksByNameHolder extends RecyclerView.ViewHolder {

    public interface  BooksListener {
        void onBooksClicked(Books books);
    }

    BooksListener listener;
    private ImageView portrait;
    private TextView book_name;
    private TextView author;
    private TextView editorial;
    private TextView isbn;
    private TextView publish_date;

    public BooksByNameHolder(@NonNull View itemView) {
        super(itemView);
        portrait = itemView.findViewById(R.id.imvPortrait);
        book_name = itemView.findViewById(R.id.tvBookName);
        author = itemView.findViewById(R.id.tvAuthor);
        editorial = itemView.findViewById(R.id.tvEditorial);
        isbn = itemView.findViewById(R.id.tvISBN);
        publish_date = itemView.findViewById(R.id.tvPublishDate);
    }

    public void setBook(final Books book, Context context){

        Picasso.get()
                .load(book.getUrl_portrait().replace("http://", "https://"))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(portrait);

        book_name.setText(book.getBook_name());
        author.setText(book.getAuthor());
        editorial.setText(book.getEditorial());
        isbn.setText(book.getIsbn());
        publish_date.setText(book.getPublish_date());
    }
}
