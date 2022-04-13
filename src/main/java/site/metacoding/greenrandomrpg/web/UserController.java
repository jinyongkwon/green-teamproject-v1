package site.metacoding.greenrandomrpg.web;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.service.user.UserService;
import site.metacoding.greenrandomrpg.web.dto.ResponseDto;
import site.metacoding.greenrandomrpg.web.dto.user.JoinDto;
import site.metacoding.greenrandomrpg.web.dto.user.LoginDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

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
    @GetMapping("/user/username/same-check")
    public @ResponseBody ResponseDto<?> usernameSameCheck(String username) {
        String data = userService.유저네임중복검사(username);
        return new ResponseDto<>(1, "통신성공", data);
    }

    // 유저닉네임 중복검사
    @GetMapping("/user/nickname/same-check")
    public @ResponseBody ResponseDto<?> nicknameSameCheck(String nickname) {
        String data = userService.닉네임중복검사(nickname);
        return new ResponseDto<>(1, "통신성공", data);
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
        User principal = (User) session.getAttribute("principal");
        if (principal != null) {
            model.addAttribute("user", true);
        } else {
            model.addAttribute("user", false);
        }
        return "main";
    }

    // @PutMapping("/main/{id}")
    // public @ResponseBody ResponseDto<?> coinUpdate(@PathVariable Integer id,
    // @RequestBody CoinUpdateDto coinupdateDto) {

    // User principal = (User) session.getAttribute("principal");

    // if (principal.getId() != id) {
    // throw new RuntimeException("동기화되지 않았다..");
    // }
    // User userCoin = userService.코인업데이트(id, coinupdateDto);
    // session.setAttribute("principal", userCoin);
    // System.out.println("코인업데이트 잘됐나 확인*************" + userCoin);
    // return new ResponseDto<>(1, "수정완료", null);
    // }

    // 준비 페이지
    @GetMapping("/ready")
    public String ready(Model model) {
        model.addAttribute("principal", true);
        model.addAttribute("ready", true);
        return "readyBattlePage";
    }

}