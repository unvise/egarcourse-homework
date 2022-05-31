package com.unvise.user;

import java.util.List;

interface UserAccount<T> {

    void addTexts(List<T> texts);

    void readTextByTitle(String title);

    List<T> getAllTexts();

    void clearTexts();

}