package co.com.dk.juanvaldez.jvsigninmc.vo.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status implements Serializable {

    private static final long serialVersionUID = -3228787783470270554L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

}
