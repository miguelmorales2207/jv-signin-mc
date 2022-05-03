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
public class ResetPasswordRequest extends RequestUserBody {

    private static final long serialVersionUID = -3228787783470270554L;

    @NotNull
    @JsonProperty("email_address")
    private String emailAddress;

    @NotNull
    @JsonProperty("vendor")
    private Integer vendor;

}
