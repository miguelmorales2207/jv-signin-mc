package co.com.dk.juanvaldez.jvsigninmc.services;

import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_AUTHENTICATE;
import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_IS_VALIDATED;
import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_LOGOUT;

import co.com.dk.juanvaldez.jvsigninmc.vo.request.AuthenticateUser;
import co.com.dk.juanvaldez.jvsigninmc.exceptions.SignInMCRestException;
import co.com.dk.juanvaldez.jvsigninmc.http.WebClientRequester;
import co.com.dk.juanvaldez.jvsigninmc.loggin.Loggin;
import co.com.dk.juanvaldez.jvsigninmc.vo.ApiResponse.UserAuthenticated;
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

    public UserAuthenticated signIn(AuthenticateUser createUser) {

        logger.log("AuthenticateUser USER...");
        UserAuthenticated userAuthenticated = authenticateUserSpoonityApi(createUser);
        logger.log("USER authentication has been performed.");

        logger.log("Validate if USER is validated.");
        boolean userIsValidated = userIsValidateSpoonityApi(
            userAuthenticated.getSessionIdentifier());
        logger.log("USER validation has been performed, continuous the USER authentication.");
        userAuthenticated.setValidated(userIsValidated);

        return userAuthenticated;
    }

    public Object signOut(String sessionId) {

        logger.log("Sign out USER...");
        Object userLoggedOut = signOutUserSpoonityApi(sessionId);
        logger.log("USER logged out.");

        return userLoggedOut;
    }

    private UserAuthenticated authenticateUserSpoonityApi(AuthenticateUser userAccess)
        throws SignInMCRestException {
        String uri = spoonityUrl + SPOONITY_USER_AUTHENTICATE;

        logger.log(String.format("Requesting external service to USER AUTHENTICATION:{}", uri));
        UserAuthenticated apiResponse = webClientRequester
            .executePostRequest(uri, userAccess)
            .bodyToMono(UserAuthenticated.class)
            .block();
        logger.log("API Response of USER AUTHENTICATION received successfully.");

        return apiResponse;
    }

    private Object signOutUserSpoonityApi(String sessionId)
        throws SignInMCRestException {
        String parameters = "?session_key=" + sessionId;
        String uri = spoonityUrl + SPOONITY_USER_LOGOUT + parameters;

        logger.log(String.format("Requesting external service to SIGN OUT USER:{}", uri));
        Object apiResponse = webClientRequester
            .executePostRequest(uri, null)
            .bodyToMono(Object.class)
            .block();
        logger.log("API Response of SIGN OUT USER received successfully.");

        return apiResponse;
    }

    private boolean userIsValidateSpoonityApi(String sessionId) throws SignInMCRestException {
        String parameters = "?session=" + sessionId;
        String uri = spoonityUrl + SPOONITY_USER_IS_VALIDATED + parameters;

        logger.log(
            String.format("Requesting external service to USER IS VALIDATED VALIDATION:{}", uri));
        UserIsValidated apiResponse = webClientRequester
            .executeGetRequest(uri)
            .bodyToMono(UserIsValidated.class)
            .block();
        logger.log("API Response of USER IS VALIDATED VALIDATION received successfully.");

        return apiResponse.isValidated();
    }

}
