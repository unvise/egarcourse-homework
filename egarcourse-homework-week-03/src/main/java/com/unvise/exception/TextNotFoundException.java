package com.unvise.exception;

import com.unvise.core.AbstractText;

public class TextNotFoundException extends TextException {

    public TextNotFoundException(String message) {
        super(message);
    }

    public TextNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextNotFoundException(Throwable cause) {
        super(cause);
    }

    public TextNotFoundException(String message, AbstractText text) {
        super(message, text);
    }

}
