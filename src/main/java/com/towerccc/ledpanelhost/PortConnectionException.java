package com.towerccc.ledpanelhost;

public class PortConnectionException extends Exception {
    PortConnectionException(String message) {
        super(message);
    }

    PortConnectionException(String message, Exception innerExcept)  {
        super(message, innerExcept);
    }
}
