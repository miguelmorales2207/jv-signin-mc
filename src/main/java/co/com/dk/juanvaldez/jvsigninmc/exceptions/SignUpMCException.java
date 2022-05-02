package co.com.dk.juanvaldez.jvsigninmc.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SignUpMCException extends Exception {

    public SignUpMCException(String message) {
        super(message);
    }
}
