package site.metacoding.greenrandomrpg.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.ranking.Ranking;
=======
>>>>>>> 23b589d38421e166aeebfcd3974e51bf662655c7
import site.metacoding.greenrandomrpg.domain.rpg.Rpg;
import site.metacoding.greenrandomrpg.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinDto {

    private String username;
    private String nickname;
    private String password;
    private String email;

    public User toEntity(Rpg rpg, Ranking ranking) {
        User user = new User();
        user.setUsername(this.username);
        user.setNickname(this.nickname);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setCoin(100000);
        user.setRpg(rpg);
        user.setRanking(ranking);
        return user;
    }

}