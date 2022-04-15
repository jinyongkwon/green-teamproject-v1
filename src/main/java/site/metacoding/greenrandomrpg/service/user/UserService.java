package site.metacoding.greenrandomrpg.service.user;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.rpg.Rpg;
import site.metacoding.greenrandomrpg.domain.rpg.RpgRepository;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.domain.user.UserRepository;
import site.metacoding.greenrandomrpg.util.email.EmailUtil;
import site.metacoding.greenrandomrpg.web.dto.user.JoinDto;
import site.metacoding.greenrandomrpg.web.dto.user.LoginDto;
import site.metacoding.greenrandomrpg.web.dto.user.PasswordResetReqDto;
import site.metacoding.greenrandomrpg.web.dto.user.UpdateDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RpgRepository rpgRepository;
    private final EmailUtil emailUtil;

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

    @Transactional
    public User 업데이트(Integer id, UpdateDto UpdateDto) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.setCoin(UpdateDto.getCoin());
            userEntity.getRpg().setAttack(UpdateDto.getAttack());
            userEntity.getRpg().setHp(UpdateDto.getHp());
            userEntity.getRpg().setMaxHp(UpdateDto.getHp());
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
        rpgRepository.save(newRpg);
        User user = joinDto.toEntity(newRpg);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        return userRepository.save(user);
    }

    public User 로그인(LoginDto loginDto) {
        System.out.println(loginDto);
        User userEntity = userRepository.mLogin(loginDto.getUsername(), loginDto.getPassword());
        System.out.println("로그인:" + userEntity);
        return userEntity;
    }

    // public User 유저수정(Integer id, User user) {
    // Optional<User> userOp = userRepository.findById(id);

    // if (userOp.isPresent()) {
    // User userEntity = userOp.get();
    // userEntity.setNickname(user.getNickname());
    // userEntity.setPassword(user.getPassword());
    // userEntity.setEmail(user.getEmail());

    // return userEntity;
    // }
    // return null;
    // }

}
