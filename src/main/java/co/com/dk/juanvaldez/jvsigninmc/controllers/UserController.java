package co.com.dk.juanvaldez.jvsigninmc.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.com.dk.juanvaldez.jvsigninmc.data.domain.AuthenticateUser;
import co.com.dk.juanvaldez.jvsigninmc.loggin.Loggin;
import co.com.dk.juanvaldez.jvsigninmc.services.ForgotPasswordService;
import co.com.dk.juanvaldez.jvsigninmc.services.SignInService;
import co.com.dk.juanvaldez.jvsigninmc.vo.ApiResponse.UserAuthenticated;
import co.com.dk.juanvaldez.jvsigninmc.vo.ApiResponseVO;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final SignInService signInService;
    private final ForgotPasswordService forgotPasswordService;
    private Loggin logger = new Loggin();

    public UserController(SignInService signInService,
        ForgotPasswordService forgotPasswordService) {
        this.signInService = signInService;
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping(value = "/authenticate", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseVO<UserAuthenticated>> LogIn(
        @Valid @RequestBody AuthenticateUser userAuthenticatedAuthenticateUser) {

        logger.log("Start USER sign in process.");
        UserAuthenticated userAuthenticated = signInService.signIn(userAuthenticatedAuthenticateUser);
        logger.log("USER signed in successfully.");

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseVO.<UserAuthenticated>builder()
                .code(HttpStatus.CREATED.value())
                .message("User has logged in successfully.")
                .data(userAuthenticated)
                .build());
    }

    @PostMapping(value = "/logout", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseVO<Object>> LogOut(
        @RequestParam(value = "session_key", required = true) String sessionId) {

        logger.log("Start USER sign out process.");
        Object userLoggedOut = signInService.signOut(sessionId);
        logger.log("USER sign out successfully.");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseVO.<Object>builder()
                .code(HttpStatus.CREATED.value())
                .message("User has successfully logged out.")
                .build());
    }

    /*@GetMapping(value = "/activate", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseVO<Object>> activate(
        @RequestParam(value = "token", required = true) String token,
        @RequestParam(value = "session_identifier", required = true) String sessionId) {

        logger.log("Start USER activation process.");
        Object userValidated = forgotPasswordService.activate(token, sessionId);
        logger.log("USER activated successfully.");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseVO.<Object>builder()
                .code(HttpStatus.OK.value())
                .message("User has been activated successfully.")
                .build());
    }*/

}
