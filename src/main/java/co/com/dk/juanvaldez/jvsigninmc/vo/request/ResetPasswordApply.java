package co.com.dk.juanvaldez.jvsigninmc.vo.request;

import co.com.dk.juanvaldez.jvsigninmc.vo.request.requestUser.RequestUserBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordApply extends RequestUserBody {

    private static final long serialVersionUID = -3228787783470270554L;

    private String token;

    private String password;

}
