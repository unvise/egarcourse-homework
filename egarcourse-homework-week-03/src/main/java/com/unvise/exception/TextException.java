package com.unvise.exception;

import com.unvise.core.AbstractText;

public class TextException extends RuntimeException {

    protected AbstractText text;

    public TextException() {
        super();
    }

    public TextException(String message) {
        super(message);
    }

    public TextException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextException(Throwable cause) {
        super(cause);
    }

    protected TextException(String message, AbstractText text) {
        super(message);
        this.text = text;
    }

}
