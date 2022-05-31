package com.unvise.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Book extends AbstractText {

    private List<BookGenre> genres;

    public Book(String title, List<String> authors, String content, Date releaseDate) {
        super(title, authors, content, releaseDate);
    }

    public Book(String title, List<String> authors, List<BookGenre> genres, String content, Date releaseDate) {
        this(title, authors, content, releaseDate);
        this.genres = genres;
    }

    @Override
    public void read() {
        String res = "Title: " + this.getTitle() + "\n" +
                "Authors: " + this.getAuthors() + "\n" +
                "Genres: " + this.genres + "\n\n" +
                this.getContent() + "\n";

        System.out.println(res);
    }

}
