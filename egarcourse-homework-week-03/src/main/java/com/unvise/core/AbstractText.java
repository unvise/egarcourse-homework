package com.unvise.core;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class AbstractText implements Readable {

    protected String title;
    protected List<String> authors;
    protected String content;
    protected Date releaseDate;

}
