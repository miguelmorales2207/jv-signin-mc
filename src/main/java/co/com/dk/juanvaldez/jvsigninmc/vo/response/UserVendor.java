package co.com.dk.juanvaldez.jvsigninmc.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVendor implements Serializable {

    private static final long serialVersionUID = -3228787783470270554L;

    @JsonProperty("language")
    private Object language;

    @JsonProperty("vendor")
    private Object vendor;

    @JsonProperty("anonymous")
    private boolean anonymous;

    @JsonProperty("reload_amount")
    private Integer reloadAmount;

    @JsonProperty("reload_threshold")
    private String reloadThreshold;

    @JsonProperty("contact_consent")
    private Integer contactConsent;

    @JsonProperty("created")
    private Date created;

    @JsonProperty("billing_profile")
    private Long billingProfile;

    @JsonProperty("tier")
    private Object tier;

    @JsonProperty("tier_since")
    private Date tierSince;

    @JsonProperty("user")
    private Object user;

}
