package com.unvise.exception;

public class TransactionFailedException extends RuntimeException {

    public TransactionFailedException() {
        super();
    }

    public TransactionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionFailedException(String message) {
        super(message);
    }

    public TransactionFailedException(Throwable cause) {
        super(cause);
    }

}
