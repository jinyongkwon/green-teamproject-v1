package site.metacoding.greenrandomrpg.service.rpg;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // @Transactional
    // public Rpg 업데이트(Integer id, UpdateDto updateDto) {
    // Optional<Rpg> rpgOp = rpgRepository.findById(id);
    // if (rpgOp.isPresent()) {
    // Rpg rpgEntity = rpgOp.get();
    // rpgEntity.setHp(updateDto.getHp());
    // return rpgEntity;
    // }
    // throw new RuntimeException("유저를 찾지 못함.");
    // }

}