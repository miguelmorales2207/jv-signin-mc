package co.com.dk.juanvaldez.jvsigninmc.data.domain;

import co.com.dk.juanvaldez.jvsigninmc.data.domain.requestUser.RequestUserBody;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticateUser extends RequestUserBody {

    private static final long serialVersionUID = -3228787783470270554L;

    @NotNull
    @JsonProperty("email_address")
    private String emailAddress;

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("vendor")
    private Integer vendor;

}
