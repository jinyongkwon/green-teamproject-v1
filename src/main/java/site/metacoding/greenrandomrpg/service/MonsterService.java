package site.metacoding.greenrandomrpg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.monster.Monster;
import site.metacoding.greenrandomrpg.domain.monster.MonsterRepository;

@RequiredArgsConstructor
@Service
public class MonsterService {
    private final MonsterRepository monsterRepository;

    public List<Monster> 몬스터상태() {
        List<Monster> mstate = monsterRepository.findAll();
        System.out.println(mstate);
        return mstate;
    }
}