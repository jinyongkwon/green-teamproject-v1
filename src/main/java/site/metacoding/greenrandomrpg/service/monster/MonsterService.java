package site.metacoding.greenrandomrpg.service.monster;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.monster.Monster;
import site.metacoding.greenrandomrpg.domain.monster.MonsterRepository;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class MonsterService {
    private final MonsterRepository monsterRepository;
    private final UserRepository userRepository;

    public void 몬스터삽입() {
        for (int j = 1; j < 40; j++) {
            for (int i = 1 + j * 50; i < 51 + j * 50; i++) {
                Random random = new Random();
                int hp = random.nextInt(100 * j) + 50 * j;
                int attack = random.nextInt((hp / 10) * (random.nextInt(5) + 1)) + 2 * j;
                Monster monster = new Monster();
                monster.setAttack(attack);
                monster.setHp(hp);
                monster.setMaxHp(hp);
                monster.setName("몬스터" + i);
                monsterRepository.save(monster);
            }
        }
    }

    public Monster 몬스터불러오기(Integer id) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            int power = userEntity.getRpg().getAttack() + userEntity.getRpg().getMaxHp();
            int minPower = (int) (power - (power * 0.1));
            int maxPower = (int) (power - (power * 0.05));
            List<Monster> monsters = monsterRepository.mFindBattleMonster(minPower, maxPower);
            Random random = new Random();
            int randomMonster = random.nextInt(monsters.size());
            Monster monster = monsters.get(randomMonster);
            return monster;
        } else {
            throw new RuntimeException("로그인 안되있음!!");
        }

    }
}