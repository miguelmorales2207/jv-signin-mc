package co.com.dk.juanvaldez.jvsigninmc.vo.request;

import co.com.dk.juanvaldez.jvsigninmc.vo.request.requestUser.RequestUserBody;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordApply extends RequestUserBody {

    private static final long serialVersionUID = -3228787783470270554L;

    @NotNull
    @JsonProperty("token")
    private String token;

    @NotNull
    @JsonProperty("password")
    private String password;

}
