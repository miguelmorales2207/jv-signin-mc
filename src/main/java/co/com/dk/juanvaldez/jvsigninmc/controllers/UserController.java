package co.com.dk.juanvaldez.jvsigninmc.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.com.dk.juanvaldez.jvsigninmc.exceptions.SignInMCException;
import co.com.dk.juanvaldez.jvsigninmc.vo.request.AuthenticateUser;
import co.com.dk.juanvaldez.jvsigninmc.vo.request.ResetPasswordApply;
import co.com.dk.juanvaldez.jvsigninmc.vo.request.ResetPasswordRequest;
import co.com.dk.juanvaldez.jvsigninmc.loggin.Loggin;
import co.com.dk.juanvaldez.jvsigninmc.services.ForgotPasswordService;
import co.com.dk.juanvaldez.jvsigninmc.services.SignInService;
import co.com.dk.juanvaldez.jvsigninmc.vo.response.UserAuthenticated;
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
        UserAuthenticated userAuthenticated = signInService
            .signIn(userAuthenticatedAuthenticateUser);
        logger.log("USER signed in successfully.");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseVO.<UserAuthenticated>builder()
                .code(HttpStatus.OK.value())
                .message("User has logged in successfully.")
                .data(userAuthenticated)
                .build());
    }

    @PostMapping(value = "/logout", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseVO> LogOut(
        @RequestParam(value = "session_key", required = true) String sessionKey)
        throws SignInMCException {

        logger.log("Start USER sign out process.");
        signInService.signOut(sessionKey);
        logger.log("USER sign out successfully.");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseVO.builder()
                .code(HttpStatus.OK.value())
                .message("User has logged out successfully.")
                .build());
    }

    @PostMapping(value = "/password-reset/reset", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseVO> resetPassword(
        @Valid @RequestBody ResetPasswordRequest resetPassword) {

        logger.log("Start send PASSWORD RESET email process.");
        forgotPasswordService.resetPasswordRequest(resetPassword);
        logger.log("PASSWORD RESET email sent successfully.");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseVO.builder()
                .code(HttpStatus.OK.value())
                .message("Password change email sent successfully.")
                .build());
    }

    @PostMapping(value = "/password-reset/apply", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseVO> resetPasswordApply(
        @Valid @RequestBody ResetPasswordApply resetPassword) {

        logger.log("Start RESET PASSWORD process.");
        forgotPasswordService.resetPasswordApply(resetPassword);
        logger.log("PASSWORD RESET process has been performed successfully.");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseVO.builder()
                .code(HttpStatus.OK.value())
                .message("Password changed successfully.")
                .build());
    }

}
