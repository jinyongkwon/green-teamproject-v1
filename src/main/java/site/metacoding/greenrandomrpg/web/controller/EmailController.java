package site.metacoding.greenrandomrpg.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.util.email.EmailUtil;

@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailUtil emailUtil;

    @GetMapping("/sendmail")
    public String sendMail() {
        // 아이디, 제목, 내용
        emailUtil.sendEmail("ysh3872@naver.com", "스프링을 이용한 메일 전송", "되는지 테스트 하는 거예요");
        return "메일 잘 보내졌어";
    }
}
