package co.com.dk.juanvaldez.jvsigninmc.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SignInMCException extends Exception {

    public SignInMCException(String message) {
        super(message);
    }
}
