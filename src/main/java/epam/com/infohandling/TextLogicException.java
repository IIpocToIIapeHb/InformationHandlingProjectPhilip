package epam.com.infohandling;

import java.io.IOException;

public class TextLogicException extends Exception {
    public TextLogicException(String message, IOException e) {
        super(message,e);
    }
}
