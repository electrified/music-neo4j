package org.maidavale.music.web.entities;

public class SuccessfulResponse {
    private final String message;

    public SuccessfulResponse(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
