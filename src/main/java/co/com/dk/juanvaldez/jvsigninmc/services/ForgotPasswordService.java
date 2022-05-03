package co.com.dk.juanvaldez.jvsigninmc.services;

import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_PASSWORD_RESET_APPLY;
import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_PASSWORD_RESET_REQUEST;

import co.com.dk.juanvaldez.jvsigninmc.vo.request.ResetPasswordApply;
import co.com.dk.juanvaldez.jvsigninmc.vo.request.ResetPasswordRequest;
import co.com.dk.juanvaldez.jvsigninmc.exceptions.SignInMCRestException;
import co.com.dk.juanvaldez.jvsigninmc.http.WebClientRequester;
import co.com.dk.juanvaldez.jvsigninmc.loggin.Loggin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Value("${spoonity.url.resource}")
    private String spoonityUrl;

    private final WebClientRequester webClientRequester;
    private Loggin logger = new Loggin();

    public ForgotPasswordService(WebClientRequester webClientRequester) {
        this.webClientRequester = webClientRequester;
    }

    public void resetPasswordRequest(ResetPasswordRequest retrievePassword) {

        logger.log("Sending token via email address for RESET PASSWORD...");
        resetPasswordSpoonityApi(retrievePassword);
        logger.log("Token for RESET PASSWORD has been sent.");
    }

    public Object resetPasswordApply(ResetPasswordApply retrievePassword) {

        logger.log("RESETTING PASSWORD...");
        Object passwordChanged = resetPasswordApplySpoonityApi(retrievePassword);
        logger.log("RESET PASSWORD has been performed.");

        return passwordChanged;
    }

    private Object resetPasswordSpoonityApi(ResetPasswordRequest resetPassword)
        throws SignInMCRestException {
        String uri = spoonityUrl + SPOONITY_USER_PASSWORD_RESET_REQUEST;

        logger
            .log(String.format("Requesting external service to SEND TOKEN RESET PASSWORD:{}", uri));
        Object apiResponse = webClientRequester
            .executePostRequest(uri, resetPassword)
            .bodyToMono(Object.class)
            .block();
        logger.log("API Response of SEND TOKEN RESET PASSWORD received successfully.");

        return apiResponse;
    }

    private Object resetPasswordApplySpoonityApi(ResetPasswordApply resetPassword)
        throws SignInMCRestException {
        String uri = spoonityUrl + SPOONITY_USER_PASSWORD_RESET_APPLY;

        logger
            .log(String.format("Requesting external service to RESET PASSWORD:{}", uri));
        Object apiResponse = webClientRequester
            .executePostRequest(uri, resetPassword)
            .bodyToMono(Object.class)
            .block();
        logger.log("API Response of RESET PASSWORD received successfully.");

        return apiResponse;
    }

}
