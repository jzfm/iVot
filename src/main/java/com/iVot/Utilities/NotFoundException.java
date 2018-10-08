package com.iVot.Utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="El parametro introducido no existe.")
public class NotFoundException extends Exception {

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException() {
        super();
    }

}
