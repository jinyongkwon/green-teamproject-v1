package site.metacoding.greenrandomrpg.web.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.config.auth.LoginUser;
import site.metacoding.greenrandomrpg.domain.ranking.Ranking;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.service.monster.MonsterService;
import site.metacoding.greenrandomrpg.service.user.UserService;
import site.metacoding.greenrandomrpg.util.Script;
import site.metacoding.greenrandomrpg.web.dto.ResponseDto;
import site.metacoding.greenrandomrpg.web.dto.user.JoinDto;
import site.metacoding.greenrandomrpg.web.dto.user.PasswordResetReqDto;
import site.metacoding.greenrandomrpg.web.dto.user.ScoreDto;
import site.metacoding.greenrandomrpg.web.dto.user.UpdateDto;
import site.metacoding.greenrandomrpg.web.dto.user.UsernameRespDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;
    private final MonsterService monsterService;

    @GetMapping("/question")
    public String questionForm() {
        return "questionForm";
    }

    // 랭킹 페이지 넘어가기
    @GetMapping("/rank")
    public String rankingform(Model model) {
        model.addAttribute("rank", true);
        return "rankingForm";
    }

    // 랭킹 유저 검색하기
    @GetMapping("/api/search")
    public @ResponseBody ResponseDto<?> search(@RequestParam(defaultValue = "") String keyword) {
        User userEntity = userService.유저찾기(keyword);
        return new ResponseDto<>(1, "성공", userEntity);
    }

    // 랭킹 가져오고 데이터 변경하기
    @GetMapping("/s/api/user-all")
    public @ResponseBody ResponseDto<?> findAllUser(Ranking ranking) {
        List<User> users = userService.랭킹순서유저가져오기();
        return new ResponseDto<>(1, "성공", users);
    }

    @PostMapping("/s/ddong/{id}")
    public @ResponseBody ResponseDto<?> scoreUpdate(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
        User principal = (User) session.getAttribute("principal");
        if (principal.getId() != id) {
            throw new RuntimeException("동기화 되지 않았다.");
        }
        User userScoreUpdate = userService.스코어업데이트(id, scoreDto);
        userService.랭킹변경하기();
        session.setAttribute("principal", userScoreUpdate);
        System.out.println("유저스코어업데이트 확인:" + userScoreUpdate);
        return new ResponseDto<>(1, "스코어수정완료", null);
    }

    // 업데이트
    @PutMapping("/s/user/{id}")
    public @ResponseBody ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {

        System.out.println(updateDto);
        User principal = (User) session.getAttribute("principal");

        if (principal.getId() != id) {
            throw new RuntimeException("동기화되지 않았다..");
        }
        User userUpdate = userService.업데이트(id, updateDto);
        session.setAttribute("principal", userUpdate);
        return new ResponseDto<>(1, "수정완료", null);
    }

    // 회원가입 데이터 전송
    @PostMapping("/join")
    public String join(JoinDto joinDto) {
        userService.회원가입(joinDto);
        return "/loginForm";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // 아이디 찾기 데이터 보내기
    @PostMapping("/id-find")
    public @ResponseBody String usernameFind(UsernameRespDto usernameRespDto) {
        userService.아이디찾기(usernameRespDto);
        return Script.close("아이디를 성공적으로 이메일로 보냈습니다.");
    }

    // 비밀번호 찾기 데이터 보내기
    @PostMapping("/password-find")
    public @ResponseBody String passwordFind(PasswordResetReqDto passwordResetReqDto) {
        userService.패스워드초기화(passwordResetReqDto);
        return Script.close("비밀번호를 성공적으로 이메일로 보냈습니다.");
    }

    // 무료뽑기 누른 현재시간 저장
    @GetMapping("/s/user/free-time")
    public @ResponseBody ResponseDto<?> freeTime(@AuthenticationPrincipal LoginUser loginUser) {
        User principal = loginUser.getUser();
        Timestamp userFreeTime = userService.무료뽑기시간저장(LocalDateTime.now(), principal.getId());
        return new ResponseDto<>(1, "성공", userFreeTime.getTime());
    }

    // 무료뽑기 누른시간 확인
    @GetMapping("/s/user/free-check")
    public @ResponseBody ResponseDto<?> freeCheck(@AuthenticationPrincipal LoginUser loginUser) {
        User principal = loginUser.getUser();
        Timestamp userFreeTime = userService.무료뽑기시간확인(principal.getId());
        return new ResponseDto<>(1, "성공", userFreeTime.getTime());
    }

    @GetMapping("/monster")
    public String monster() throws InterruptedException {
        monsterService.몬스터삽입();
        Thread.sleep(5000);
        return "redirect:/";
    }

}