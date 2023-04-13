package com.nashss.se.musicplaylistservice.exceptions;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5312827183453299865L;

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(Throwable cause) {
        super(cause);
    }

}
