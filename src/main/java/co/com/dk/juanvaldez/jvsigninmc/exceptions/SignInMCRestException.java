package co.com.dk.juanvaldez.jvsigninmc.exceptions;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInMCRestException extends RuntimeException {

    private int httpStatusCode;

    private List<String> errors;

    private Object data;

    public SignInMCRestException(String message) {
        super(message);
    }

    public SignInMCRestException(String message, int httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public SignInMCRestException(String message, int httpStatusCode, List<String> errors,
        Object data) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.errors = errors;
        this.data = data;
    }

}
