package site.metacoding.greenrandomrpg.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordResetReqDto {

    private String username;
    private String email;
}
