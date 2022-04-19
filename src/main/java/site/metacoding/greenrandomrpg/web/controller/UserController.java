package site.metacoding.greenrandomrpg.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.service.user.UserService;
import site.metacoding.greenrandomrpg.web.dto.ResponseDto;
import site.metacoding.greenrandomrpg.web.dto.user.JoinDto;
import site.metacoding.greenrandomrpg.web.dto.user.LoginDto;
import site.metacoding.greenrandomrpg.web.dto.user.PasswordResetReqDto;
import site.metacoding.greenrandomrpg.web.dto.user.UpdateDto;
import site.metacoding.greenrandomrpg.web.dto.user.UsernameRespDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    // 업데이트
    @PutMapping("/s/user/{id}")
    public @ResponseBody ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {

        System.out.println(updateDto);
        User principal = (User) session.getAttribute("principal");
        System.out.println("principal" + principal);

        if (principal.getId() != id) {
            throw new RuntimeException("동기화되지 않았다..");
        }
        User userUpdate = userService.업데이트(id, updateDto);
        session.setAttribute("principal", userUpdate);
        System.out.println("업데이트 잘됐나 확인*************" + userUpdate);
        return new ResponseDto<>(1, "수정완료", null);
    }

    // 수정 페이지 이동
    @GetMapping("/s/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    // @PutMapping("/s/user/{id}")
    // public @ResponseBody ResponseDto<String> update(@PathVariable Integer id,
    // @RequestBody User user) {

    // User principal = (User) session.getAttribute("principal");

    // if (principal == null) {
    // return new ResponseDto<>(-1, "인증안됨", null);
    // }
    // User userEntity = userService.유저수정(id, user);
    // session.setAttribute("principal", userEntity); // name , value

    // return new ResponseDto<>(1, "성공", null);
    // }

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

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String joinForm() {
        return "joinForm";
    }

    // 로그인 페이지
    @GetMapping("/login-form")
    public String loginForm(HttpServletRequest request, Model model) {
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();

            for (Cookie cookie : cookies) {
                System.out.println("쿠키값:" + cookie.getName());
                if (cookie.getName().equals("remember")) {
                    model.addAttribute("remember", cookie.getValue());
                }
            }
        }
        return "loginForm";
    }

    // 회원가입 데이터 전송
    @PostMapping("/join")
    public String join(JoinDto joinDto) {
        userService.회원가입(joinDto);
        return "/loginForm";
    }

    // 로그인 데이터 전송
    @PostMapping("/login")
    public String login(LoginDto loginDto, HttpServletResponse response) {
        User userEntity = userService.로그인(loginDto);
        if (userEntity != null) {
            session.setAttribute("principal", userEntity);
            if (loginDto.getRemember() != null && loginDto.getRemember().equals("on")) {
                response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername());
            }
            return "redirect:/";
        } else {
            return "redirect:/login-form";
        }
    }

    // 메인페이지
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("main", true);
        return "main";
    }

    // 준비 페이지
    @GetMapping("/s/ready")
    public String ready(Model model) {
        model.addAttribute("ready", true);
        return "readyBattlePage";
    }

    // 상점 페이지
    @GetMapping("/s/store")
    public String store(Model model) {
        model.addAttribute("store", true);
        return "store";
    }

    // 전투 페이지
    @GetMapping("/s/battle")
    public String battle(Model model) {
        return "battlePage";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        System.out.println("로그아웃 잘 작동하고 있나 확인");
        return "redirect:/";
    }

    // 아이디 찾기 페이지
    @GetMapping("/id-find-form")
    public String findId() {
        return "idFindForm";
    }

    // 아이디 찾기 데이터 보내기
    @PostMapping("/id-find")
    public String usernameFind(UsernameRespDto usernameRespDto) {
        userService.아이디찾기(usernameRespDto);
        System.out.println("아이디 찾기 작동중인지 controller에서 확인하기");
        return "redirect:/";
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/password-find-form")
    public String passwordfindForm(Model model) {
        return "passwordFindForm";
    }

    // 비밀번호 찾기 데이터 보내기
    @PostMapping("/password-find")
    public String passwordFind(PasswordResetReqDto passwordResetReqDto) {
        userService.패스워드초기화(passwordResetReqDto);
        return "redirect:/";
    }

    // 채팅페이지
    @GetMapping("/s/chat")
    public String chat() {
        return "chatPage";
    }

    // 똥피하기페이지
    @GetMapping("/s/ddong")
    public String ddong(Model model) {
        model.addAttribute("ddong", true);
        return "ddong";
    }

}