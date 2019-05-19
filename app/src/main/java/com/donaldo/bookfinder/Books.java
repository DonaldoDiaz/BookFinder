package com.donaldo.bookfinder;

public class Books {
    private String url_portrait;
    private String book_name;
    private String author;
    private String editorial;
    private String isbn;
    private String publish_date;

    public Books(String url_portrait, String book_name, String author, String editorial, String isbn, String publish_date) {
        this.url_portrait = url_portrait;
        this.book_name = book_name;
        this.author = author;
        this.editorial = editorial;
        this.isbn = isbn;
        this.publish_date = publish_date;
    }

    public String getUrl_portrait() {
        return url_portrait;
    }

    public void setUrl_portrait(String url_portrait) {
        this.url_portrait = url_portrait;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }
}
