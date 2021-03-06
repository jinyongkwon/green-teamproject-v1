package site.metacoding.greenrandomrpg.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDto {

    private Integer coin; // User
    private Integer attack; // Rpg
    private Integer maxHp;
    private Integer hp;

    private Integer html;
    private Integer java;
    private Integer jsp;
    private Integer spring;

}
