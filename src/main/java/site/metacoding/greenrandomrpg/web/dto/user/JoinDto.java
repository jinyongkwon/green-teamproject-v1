package site.metacoding.greenrandomrpg.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.greenrandomrpg.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinDto {
    private String username;
    private String nickname;
    private String password;
    private String email;

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setNickname(this.nickname);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setCoin(0);
        return user;
    }

}