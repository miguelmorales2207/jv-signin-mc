package co.com.dk.juanvaldez.jvsigninmc.services;

import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_AUTHENTICATE;
import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_IS_VALIDATED;
import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_LOGOUT;

import co.com.dk.juanvaldez.jvsigninmc.data.domain.Authenticate;
import co.com.dk.juanvaldez.jvsigninmc.exceptions.SignUpMCRestException;
import co.com.dk.juanvaldez.jvsigninmc.http.WebClientRequester;
import co.com.dk.juanvaldez.jvsigninmc.loggin.Loggin;
import co.com.dk.juanvaldez.jvsigninmc.vo.ApiResponse.Authenticated;
import co.com.dk.juanvaldez.jvsigninmc.vo.ApiResponse.UserIsValidated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Value("${spoonity.url.resource}")
    private String spoonityUrl;

    private final WebClientRequester webClientRequester;
    private Loggin logger = new Loggin();

    public SignInService(WebClientRequester webClientRequester) {
        this.webClientRequester = webClientRequester;
    }

    public Authenticated signIn(Authenticate createUser) {

        logger.log("Creating new USER...");
        Authenticated userAuthenticated = authenticateUserSpoonityApi(createUser);
        logger.log("New USER has been created.");

        logger.log("Validate if the USER email or cedula exists.");
        boolean userIsValidated = userIsValidateSpoonityApi(
            userAuthenticated.getSessionIdentifier());
        userAuthenticated.setValidated(userIsValidated);
        logger.log("Email and cedula does not exists, continuous the USER register process.");

        return userAuthenticated;
    }

    public Object signOut(String sessionId) {

        logger.log("Validate if the USER email or cedula exists.");
        Object userLoggedOut = signOutUserSpoonityApi(sessionId);
        logger.log("Email and cedula does not exists, continuous the USER register process.");

        return userLoggedOut;
    }

    private Authenticated authenticateUserSpoonityApi(Authenticate userAccess)
        throws SignUpMCRestException {
        String uri = spoonityUrl + SPOONITY_USER_AUTHENTICATE;

        logger.log(String.format("Requesting external service to CREATE USER:{}", uri));
        Authenticated apiResponse = webClientRequester
            .executePostRequest(uri, userAccess)
            .bodyToMono(Authenticated.class)
            .block();
        logger.log("API Response of CREATE USER received successfully.");

        return apiResponse;
    }

    private Object signOutUserSpoonityApi(String sessionId)
        throws SignUpMCRestException {
        String parameters = "?session_key=" + sessionId;
        String uri = spoonityUrl + SPOONITY_USER_LOGOUT + parameters;

        logger.log(String.format("Requesting external service to VALIDATE USER EMAIL:{}", uri));
        Object apiResponse = webClientRequester
            .executePostRequest(uri, null)
            .bodyToMono(Object.class)
            .block();
        logger.log("API Response of VALIDATE USER EMAIL received successfully.");

        return apiResponse;
    }

    private boolean userIsValidateSpoonityApi(String sessionId) throws SignUpMCRestException {
        String parameters = "?session=" + sessionId;
        String uri = spoonityUrl + SPOONITY_USER_IS_VALIDATED + parameters;

        logger.log(String.format("Requesting external service to VALIDATE USER EMAIL:{}", uri));
        UserIsValidated apiResponse = webClientRequester
            .executeGetRequest(uri)
            .bodyToMono(UserIsValidated.class)
            .block();
        logger.log("API Response of VALIDATE USER EMAIL received successfully.");

        return apiResponse.isValidated();
    }

}
