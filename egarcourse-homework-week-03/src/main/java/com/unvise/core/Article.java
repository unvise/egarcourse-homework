package com.unvise.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Article extends AbstractText {

    private String scientificDirector;

    public Article(String title, List<String> authors, String content, Date releaseDate) {
        super(title, authors, content, releaseDate);
    }

    public Article(String title, String scientificDirector, List<String> authors, String content, Date releaseDate) {
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
