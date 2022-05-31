package com.unvise.homework.text;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Article extends PrintedEdition {

    private String scientificDirector;

    public Article(String title, List<String> authors, String content, LocalDate releaseDate) {
        super(title, authors, content, releaseDate);
    }

    public Article(String title, String scientificDirector, List<String> authors, String content, LocalDate releaseDate) {
        this(title, authors, content, releaseDate);
        this.scientificDirector = scientificDirector;
    }

    @Override
    public void read() {
        String res = "Title: " + this.getTitle() + "\n" +
                "ScientificDirector: " + this.getScientificDirector() + "\n" +
                "Authors: " + this.getAuthors() + "\n\n" +
                this.getContent() + "\n";

        System.out.println(res);

    }

}
