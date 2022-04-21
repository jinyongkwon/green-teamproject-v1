package site.metacoding.greenrandomrpg.web.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.config.auth.LoginUser;
import site.metacoding.greenrandomrpg.domain.monster.Monster;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.service.monster.MonsterService;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final MonsterService monsterService;

    // 수정 페이지 이동
    @GetMapping("/s/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
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
    public String battle(Model model, @AuthenticationPrincipal LoginUser loginUser) {
        Monster monster = monsterService.몬스터불러오기(loginUser.getUser().getId());
        model.addAttribute("monster", monster);
        return "battlePage";
    }

    // 아이디 찾기 페이지
    @GetMapping("/id-find-form")
    public String findId() {
        return "idFindForm";
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/password-find-form")
    public String passwordfindForm(Model model) {
        return "passwordFindForm";
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
