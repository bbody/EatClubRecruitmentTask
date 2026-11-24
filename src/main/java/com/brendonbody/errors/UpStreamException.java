package com.brendonbody.errors;

public class UpStreamException extends RuntimeException {
    public UpStreamException(String service, String message, Throwable cause) {
        super(upstreamErrorMessage(service, message), cause);
    }

    public UpStreamException(String service, String message) {
        super(upstreamErrorMessage(service, message));
    }

    private static String upstreamErrorMessage(String service, String message) {
        return "Upstream error with %s: %s".formatted(service, message);
    }
}
