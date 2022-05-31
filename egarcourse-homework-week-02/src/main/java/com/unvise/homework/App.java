package com.unvise.homework;

import com.unvise.homework.text.Readable;
import com.unvise.homework.text.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        PrintedEdition[] printedEditions = {
                new Book("Clean Code", List.of("Robert Martin"), List.of(BookGenre.FANTASY, BookGenre.HORROR, BookGenre.MYSTERY),
                        "Hello World1", LocalDate.of(2008, Month.AUGUST, 1)),

                new Article("Design Database with Room", "Alex Williams", List.of("Tom Johnson"),
                        "Hello World2", LocalDate.of(2022, Month.MARCH, 12))
        };

        Arrays.stream(printedEditions).forEach(Readable::read);

        System.out.println("Instances = " + Arrays.toString(printedEditions));
    }

}
