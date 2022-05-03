package co.com.dk.juanvaldez.jvsigninmc.vo.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticated implements Serializable {

    private static final long serialVersionUID = -3228787783470270554L;

    @JsonProperty("first_login")
    private boolean firstLogin;

    @JsonProperty("online_order_token")
    private String onlineOrderToken;

    @JsonProperty("vendor_user")
    private String vendorUser;

    @JsonProperty("session_identifier")
    private String sessionIdentifier;

    //@JsonProperty("user_vendor")
    //private UserVendor userVendor;

    @JsonProperty("is_validated")
    private boolean isValidated;

}
