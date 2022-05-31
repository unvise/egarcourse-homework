package com.unvise.user;

import com.unvise.core.AbstractText;
import com.unvise.core.Readable;
import com.unvise.exception.TextNotFoundException;

import java.util.List;
import java.util.Objects;

public class UserAccountImpl<ID extends Number> extends AbstractAccount<ID> implements UserAccount<AbstractText> {

    private final List<AbstractText> texts;

    public UserAccountImpl(ID id, List<AbstractText> texts) {
        super(id);

        this.texts = texts;
    }

    @Override
    public void addTexts(List<AbstractText> texts) {
        this.texts.addAll(texts);
    }

    @Override
    public void readTextByTitle(String title) throws TextNotFoundException{
        List<String> textsByTitle = texts.stream()
                .map(AbstractText::getTitle)
                .toList();

        if (textsByTitle.contains(title)) {
            texts.stream()
                    .filter(x -> x.getTitle().equals(title))
                    .forEach(Readable::read);
        } else {
            throw new TextNotFoundException("Cannot find text with title: " + title);
        }
    }

    @Override
    public List<AbstractText> getAllTexts() {
        return texts;
    }

    @Override
    public void clearTexts() {
        this.texts.clear();
    }

}


