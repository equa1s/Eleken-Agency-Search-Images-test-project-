package com.imagesearcher.api.exceptions;

import java.io.IOException;

/**
 * @author equa1s.
 */
public class NonSuccessfulResponseCodeException extends IOException {

    public NonSuccessfulResponseCodeException() {
        super();
    }

    public NonSuccessfulResponseCodeException(String s) {
        super(s);
    }

}
