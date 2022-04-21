package site.metacoding.greenrandomrpg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.service.user.UserService;
import site.metacoding.greenrandomrpg.web.dto.ResponseDto;

@RequiredArgsConstructor
@Controller
public class CheckController {

    private final UserService userService;

    // 유저네임 중복검사
    @GetMapping("/api/user/username-same-check")
    public @ResponseBody ResponseDto<?> usernameSameCheck(String username) {
        boolean isNotSame = userService.유저네임중복검사(username);
        if (isNotSame) {
            return new ResponseDto<>(1, "검사성공", isNotSame);
        } else {
            return new ResponseDto<>(1, "이미 사용중인 아이디입니다!!", isNotSame);
        }
    }

    // 유저 이메일 중복검사
    @GetMapping("/api/user/email-same-check")
    public @ResponseBody ResponseDto<?> emailSameCheck(String email) {
        boolean isNotSame = userService.유저이메일중복검사(email);
        if (isNotSame) {
            return new ResponseDto<>(1, "검사성공", isNotSame);
        } else {
            return new ResponseDto<>(1, "이미 사용중인 이메일 입니다!!", isNotSame);
        }
    }

    // 유저닉네임 중복검사
    @GetMapping("/api/user/nickname-same-check")
    public @ResponseBody ResponseDto<?> nicknameSameCheck(String nickname) {
        boolean isNotSame = userService.닉네임중복검사(nickname);
        if (isNotSame) {
            return new ResponseDto<>(1, "검사성공", isNotSame);
        } else {
            return new ResponseDto<>(1, "이미 사용중인 닉네임입니다!!", isNotSame);
        }
    }
}
