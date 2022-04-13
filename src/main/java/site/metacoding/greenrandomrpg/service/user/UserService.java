package site.metacoding.greenrandomrpg.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.domain.user.UserRepository;
import site.metacoding.greenrandomrpg.web.dto.user.JoinDto;
import site.metacoding.greenrandomrpg.web.dto.user.LoginDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // @Transactional
    // public User 코인업데이트(Integer id, CoinUpdateDto coinUpdateDto) {
    // Optional<User> userCoinOp = userRepository.findById(id);
    // if (userCoinOp.isPresent()) {
    // User userEntity = userCoinOp.get();
    // userEntity.setCoin(coinUpdateDto.getCoin());
    // return userEntity;
    // }
    // return null;
    // }

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

    @Transactional
    public User 회원가입(JoinDto joinDto) {
        return userRepository.save(joinDto.toEntity());
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
