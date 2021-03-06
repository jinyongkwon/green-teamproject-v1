package site.metacoding.greenrandomrpg.service.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.ranking.Ranking;
import site.metacoding.greenrandomrpg.domain.ranking.RankingRepository;
import site.metacoding.greenrandomrpg.domain.rpg.Rpg;
import site.metacoding.greenrandomrpg.domain.rpg.RpgRepository;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.domain.user.UserRepository;
import site.metacoding.greenrandomrpg.util.email.EmailUtil;
import site.metacoding.greenrandomrpg.web.dto.user.JoinDto;
import site.metacoding.greenrandomrpg.web.dto.user.PasswordResetReqDto;
import site.metacoding.greenrandomrpg.web.dto.user.ScoreDto;
import site.metacoding.greenrandomrpg.web.dto.user.UpdateDto;
import site.metacoding.greenrandomrpg.web.dto.user.UsernameRespDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RpgRepository rpgRepository;
    private final RankingRepository rankingRepository;
    private final EmailUtil emailUtil;

    @Transactional
    public List<User> 랭킹순서유저가져오기() {
        List<User> userEntity = userRepository.findAllByRankingDesc();
        return userEntity;
    }

    @Transactional
    public void 랭킹변경하기() {
        List<Ranking> rankingUpdate = rankingRepository.findAllRanking();

        for (int i = 0; i < rankingUpdate.size(); i++) { // 사이즈 4번 돌고
            rankingUpdate.get(i).setRanking(i + 1);
        }
    }

    public User 유저찾기(String keyword) {
        Optional<User> userSearch = userRepository.mSearch(keyword);
        if (userSearch.isPresent()) {
            User userEntity = userSearch.get();
            return userEntity;
        } else {
            return null;
        }
    }

    public Timestamp 무료뽑기시간확인(Integer userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            return userEntity.getRpg().getFormatFreeTime();
        } else {
            throw new RuntimeException("없는 아이디!!!");
        }
    }

    @Transactional
    public Timestamp 무료뽑기시간저장(LocalDateTime now, Integer userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.getRpg().setFreeTime(now);
            return userEntity.getRpg().getFormatFreeTime();
        } else {
            throw new RuntimeException("없는 아이디!!!");
        }
    }

    // 패스워드 초기화
    @Transactional
    public void 패스워드초기화(PasswordResetReqDto passwordResetReqDto) {
        Optional<User> userOp = userRepository.findByUsernameAndEmail(passwordResetReqDto.getUsername(),
                passwordResetReqDto.getEmail());

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            String encPassword = bCryptPasswordEncoder.encode("0000");
            userEntity.setPassword(encPassword);

            emailUtil.sendEmail(userEntity.getEmail(), "비밀번호 새로 보내드립니다", "초기화된 비밀번호는 0000 입니다.");
        } else {
            throw new RuntimeException("이메일이 없는디?");
        }
    }

    // 아이디 찾기
    @Transactional
    public void 아이디찾기(UsernameRespDto usernameRespDto) {
        Optional<User> userOp = userRepository.findUsernameByEmail(usernameRespDto.getEmail());
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.getUsername();
            userEntity.getEmail();
            emailUtil.sendEmail(userEntity.getEmail(), "귀하의 아이디를 보냅니다", "귀하의 아이디는:" + userEntity.getUsername());
        } else {
            throw new RuntimeException("이메일을 보내는데 실패했습니다");
        }
    }

    @Transactional
    public User 스코어업데이트(Integer id, ScoreDto scoreDto) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            if (userEntity.getRanking().getScore() < scoreDto.getScore()) {
                userEntity.getRanking().setScore(scoreDto.getScore());
            }
            // userEntity.getRanking().setRank(scoreDto.getRank());
            return userEntity;
        }
        return null;
    }

    @Transactional
    public User 업데이트(Integer id, UpdateDto UpdateDto) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.setCoin(UpdateDto.getCoin());
            userEntity.getRpg().setAttack(UpdateDto.getAttack());
            userEntity.getRpg().setHp(UpdateDto.getHp());
            userEntity.getRpg().setMaxHp(UpdateDto.getMaxHp());
            userEntity.getRpg().setJava(UpdateDto.getJava());
            userEntity.getRpg().setHtml(UpdateDto.getHtml());
            userEntity.getRpg().setJsp(UpdateDto.getJsp());
            userEntity.getRpg().setSpring(UpdateDto.getSpring());
            return userEntity;
        }
        return null;
    }

    public boolean 유저네임중복검사(String username) {
        System.out.println(username);
        User userEntity = userRepository.mUsernameSameCheck(username);

        if (userEntity == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean 닉네임중복검사(String nickname) {
        User userEntity = userRepository.mNicknameSameCheck(nickname);

        if (userEntity == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean 유저이메일중복검사(String email) {

        User userEntity = userRepository.mEmailSameCheck(email);

        if (userEntity == null) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public User 회원가입(JoinDto joinDto) {
        Rpg newRpg = new Rpg();
        newRpg.setAttack(10);
        newRpg.setHp(100);
        newRpg.setMaxHp(100);
        newRpg.setHtml(0);
        newRpg.setJava(0);
        newRpg.setJsp(0);
        newRpg.setSpring(0);
        rpgRepository.save(newRpg);
        Ranking newRanking = new Ranking();
        newRanking.setScore(0);
        newRanking.setRanking(0);
        rankingRepository.save(newRanking);
        User user = joinDto.toEntity(newRpg, newRanking);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        return userRepository.save(user);
    }

}
