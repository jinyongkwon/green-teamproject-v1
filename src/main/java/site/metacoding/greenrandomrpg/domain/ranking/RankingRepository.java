package site.metacoding.greenrandomrpg.domain.ranking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RankingRepository extends JpaRepository<Ranking, Integer> {
    @Query(value = "SELECT * FROM Ranking ORDER BY score DESC ;", nativeQuery = true)
    List<Ranking> findAllRanking();

}
