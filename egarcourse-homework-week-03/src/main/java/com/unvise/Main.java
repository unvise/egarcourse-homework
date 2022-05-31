package com.unvise;

import com.unvise.core.Book;
import com.unvise.exception.TextIllegalStateException;
import com.unvise.user.UserAccountImpl;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserAccountImpl<Integer> userAccount = new UserAccountImpl<>(1, List.of(
                new Book("Clean Architecture", List.of("Robert C. Martin"), "Clean Architecture is basically one idea repeated over and over for 30 chapters.", new Date()),
                new Book("Song of Ice and Fire", List.of("George R. R. Martin"), "A Song of Ice and Fire is a series of epic fantasy novels by the American novelist and screenwriter George R. R. Martin.", new Date()),
                new Book("Mickey7", List.of("Edward Ashton"), "Mickey7 is an Expendable: a disposable employee on a human expedition sent to colonize the ice world Niflheim.", new Date()) // specially
        ));

        try {
            userAccount.readTextByTitle("Clean Architecture");
            userAccount.readTextByTitle("Mickey7");
            userAccount.readTextByTitle("Mikkey7");
        } catch (RuntimeException e) {
            throw new TextIllegalStateException(e);
        }
    }
}
