package co.com.dk.juanvaldez.jvsigninmc.services;

import static co.com.dk.juanvaldez.jvsigninmc.constants.WebURIConstants.SPOONITY_USER_AUTHENTICATE;

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
        boolean userIsValidated = userIsValidate(userAuthenticated.getSessionIdentifier());
        userAuthenticated.setValidated(userIsValidated);
        logger.log("Email and cedula does not exists, continuous the USER register process.");

        return userAuthenticated;
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

    /*private void validateUserExists(User user) throws BusinessRuleException {
        logger.log("Validate if the USER email exists.");
        boolean userValidation = userEmailExistsSpoonityApi(user.getEmailAddress());
        if (userValidation) {
            throw new BusinessRuleException(
                String.format("Usuario con Email %1$s ya existe.", user.getEmailAddress()));
        }

        logger.log("Validate if the USER cedula exists.");
        boolean cedulaExists = userCedulaExistsSpoonityApi(user.getCedula(), user.getVendor());
        if (cedulaExists) {
            throw new BusinessRuleException(
                String.format("Usuario con Cedula %1$s ya existe.", user.getCedula()));
        }

        logger.log("Validate if the USER mobile exists.");
        boolean mobileExists = userMobileExistsSpoonityApi(user.getVendor(),
            user.getPhoneNumber().getNumber());
        if (mobileExists) {
            throw new BusinessRuleException(
                String.format("Usuario con Mobile %1$s ya existe.", user.getCedula()));
        }

    }*/

    private boolean userIsValidate(String sessionId) throws SignUpMCRestException {
        String parameters = "?session=" + sessionId;
        String uri = spoonityUrl + SPOONITY_USER_AUTHENTICATE + parameters;

        logger.log(String.format("Requesting external service to VALIDATE USER EMAIL:{}", uri));
        UserIsValidated apiResponse = webClientRequester
            .executeGetRequest(uri)
            .bodyToMono(UserIsValidated.class)
            .block();
        logger.log("API Response of VALIDATE USER EMAIL received successfully.");

        return apiResponse.isValidated();
    }

    /*private boolean userCedulaExistsSpoonityApi(String cedula, Integer vendor)
        throws SignUpMCRestException {
        String parameters = "?cedula=" + cedula + "&vendor=" + vendor;
        String uri = spoonityUrl + SPOONITY_USER_CEDULA_EXISTS + parameters;

        logger.log(String.format("Requesting external service to VALIDATE USER CEDULA:{}", uri));
        UserValidation apiResponse = webClientRequester
            .executeGetRequest(uri)
            .bodyToMono(UserValidation.class)
            .block();
        logger.log("API Response of VALIDATE USER CEDULA received successfully.");

        return apiResponse.getExists();
    }

    private boolean userMobileExistsSpoonityApi(Integer vendor, Long phone)
        throws SignUpMCRestException {
        String parameters = "?vendor=" + vendor + "&mobile=" + phone;
        String uri = spoonityUrl + SPOONITY_USER_MOBILE_EXISTS + parameters;

        logger.log(String.format("Requesting external service to VALIDATE USER MOBILE:{}", uri));
        UserValidation apiResponse = webClientRequester
            .executeGetRequest(uri)
            .bodyToMono(UserValidation.class)
            .block();
        logger.log("API Response of VALIDATE USER MOBILE received successfully.");

        return apiResponse.getExists();
    }*/

}
