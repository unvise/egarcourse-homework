package com.unvise.homework.text;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class PrintedEdition implements Readable {

    protected String title;
    protected List<String> authors;
    protected String content;
    protected LocalDate releaseDate;

}
