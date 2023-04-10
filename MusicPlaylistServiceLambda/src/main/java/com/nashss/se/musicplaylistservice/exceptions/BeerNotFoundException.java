package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given AlbumTrack ASIN and track number is not found
 * in the database.
 */
public class BeerNotFoundException extends RuntimeException {


    private static final long serialVersionUID = -5353057381173280369L;

    /**
     * Exception with no message or cause.
     */
    public BeerNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public BeerNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public BeerNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public BeerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
