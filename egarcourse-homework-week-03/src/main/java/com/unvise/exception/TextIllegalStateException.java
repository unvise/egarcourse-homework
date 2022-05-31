package com.unvise.exception;

import com.unvise.core.AbstractText;

public class TextIllegalStateException extends TextException {

    public TextIllegalStateException(String message) {
        super(message);
    }

    public TextIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextIllegalStateException(Throwable cause) {
        super(cause);
    }

    public TextIllegalStateException(String message, AbstractText text) {
        super(message, text);
    }

}
