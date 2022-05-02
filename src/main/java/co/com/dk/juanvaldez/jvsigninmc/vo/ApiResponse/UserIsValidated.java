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
public class UserIsValidated implements Serializable {

    private static final long serialVersionUID = -3228787783470270554L;

    @JsonProperty("isValidated")
    private boolean isValidated;

}
