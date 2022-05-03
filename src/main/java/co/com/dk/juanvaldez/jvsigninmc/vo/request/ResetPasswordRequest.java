package co.com.dk.juanvaldez.jvsigninmc.vo.request;

import co.com.dk.juanvaldez.jvsigninmc.vo.request.requestUser.RequestUserBody;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest extends RequestUserBody {

    private static final long serialVersionUID = -3228787783470270554L;

    @JsonProperty("email_address")
    private String emailAddress;

    private Integer vendor;

}
