package com.iVot.Utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Ya has votado en este tema.")
public class AlreadyVoteException extends Exception {

    public AlreadyVoteException(String msg) {
        super(msg);
    }

    public AlreadyVoteException() {
        super();
    }

}