package site.metacoding.greenrandomrpg.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM User WHERE username = :username AND password =:password", nativeQuery = true)
    User mLogin(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT * FROM User WHERE username = :username", nativeQuery = true)
    User mUsernameSameCheck(@Param("username") String username);

    @Query(value = "SELECT * FROM User WHERE nickname = :nickname", nativeQuery = true)
    User mNicknameSameCheck(@Param("nickname") String nickname);

    @Query(value = "SELECT * FROM User WHERE email = :email", nativeQuery = true)
    User mEmailSameCheck(@Param("email") String email);

    @Query(value = "SELECT * FROM User WHERE username = :username AND email =:email", nativeQuery = true)
    Optional<User> findByUsernameAndEmail(@Param("username") String username, @Param("email") String email);

    @Query(value = "SELECT * FROM User WHERE username =:username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM User WHERE email =:email", nativeQuery = true)
    Optional<User> findUsernameByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM User WHERE rpgId =:rpgId", nativeQuery = true)
    User findUserByRpgId(@Param("rpgId") Integer rpgId);

    @Query(value = "SELECT * FROM User WHERE rankingId =:rankingId", nativeQuery = true)
    User findUserByRankingId(@Param("rankingId") Integer rankingId);

    @Query(value = "SELECT * FROM User WHERE nickname = :keyword", nativeQuery = true)
    Optional<User> mSearch(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM user u left outer join ranking r on u.id = r.id ORDER BY score desc", nativeQuery = true)
    List<User> findAllByRankingDesc();

}
