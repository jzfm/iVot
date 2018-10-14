package com.iVot.Utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="El evento no esta disponible.")
public class EventIsNotAvailableException extends Exception {

    public EventIsNotAvailableException(String msg) {
        super(msg);
    }

    public EventIsNotAvailableException() {
        super();
    }

}