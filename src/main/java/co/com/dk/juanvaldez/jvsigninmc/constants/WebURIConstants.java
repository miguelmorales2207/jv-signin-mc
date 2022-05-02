package co.com.dk.juanvaldez.jvsigninmc.constants;

public class WebURIConstants {

    //authentication
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";

    public static final String BEARER = "Bearer ";

    public static final String AUDIENCE_SERVICE = "service";
    public static final String AUDIENCE_PUBLIC = "public";
    public static final String AUDIENCE_INTERNAL = "internal";

    public static final Long USER_SERVICE = 0L;

    //SPOONITY APIs
    public static final String SPOONITY_USER_IS_VALIDATED = "/user/isValidated";

    public static final String SPOONITY_USER_AUTHENTICATE = "/user/authenticate";
    public static final String SPOONITY_USER_LOGOUT = "/user/logout";

    public static final String SPOONITY_USER_PASSWORD_RESET_REQUEST = "/user/password-reset/reset";
    public static final String SPOONITY_USER_PASSWORD_RESET_APPLY = "/user/password-reset/apply";

    //PERMISSIONS
    public static final String SERVICE = "SERVICE";
    public static final String PUBLIC = "PUBLIC";

}
