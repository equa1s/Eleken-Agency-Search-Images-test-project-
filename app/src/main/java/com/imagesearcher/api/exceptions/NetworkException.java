package com.imagesearcher.api.exceptions;

import java.io.IOException;

/**
 * @author equa1s.
 */
public class NetworkException extends IOException {
    public NetworkException() {
    }

    public NetworkException(String detailMessage) {
        super(detailMessage);
    }
}
