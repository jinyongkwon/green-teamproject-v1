package site.metacoding.greenrandomrpg.service.rpg;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.rpg.Rpg;
import site.metacoding.greenrandomrpg.domain.rpg.RpgRepository;

@RequiredArgsConstructor
@Service
public class RpgService {
    private final RpgRepository rpgRepository;

    public List<Rpg> 알피지상태() {
        List<Rpg> rstate = rpgRepository.findAll();
        System.out.println(rstate);
        return rstate;
    }

    // userEntity.getRpg().setAttack(UpdateDto.getAttack());
    // userEntity.getRpg().setHp(UpdateDto.getHp());
    // userEntity.getRpg().setMaxHp(UpdateDto.getHp());
    // userEntity.getRpg().setJava(UpdateDto.getJava());
    // userEntity.getRpg().setHtml(UpdateDto.getHtml());
    // userEntity.getRpg().setJsp(UpdateDto.getJsp());
    // userEntity.getRpg().setSpring(UpdateDto.getSpring());

}