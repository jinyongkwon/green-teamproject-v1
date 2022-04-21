package site.metacoding.greenrandomrpg.domain.monster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonsterRepository extends JpaRepository<Monster, Integer> {

    @Query(value = "SELECT * FROM monster WHERE :minUserPower<attack+maxhp AND attack+maxhp<:maxUserPower", nativeQuery = true)
    List<Monster> mFindBattleMonster(@Param("minUserPower") Integer minUserPower,
            @Param("maxUserPower") Integer maxUserPower);
}