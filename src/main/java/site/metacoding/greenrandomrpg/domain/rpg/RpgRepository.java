package site.metacoding.greenrandomrpg.domain.rpg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RpgRepository extends JpaRepository<Rpg, Integer> {
    @Query(value = "SELECT * FROM Rpg WHERE id =:id", nativeQuery = true)
    Rpg findRpgIdById(@Param("id") Integer id);

}