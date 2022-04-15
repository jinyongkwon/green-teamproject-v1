package site.metacoding.greenrandomrpg.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.websocket.Greeting;
import site.metacoding.greenrandomrpg.domain.websocket.Message;

@RequiredArgsConstructor
@Controller
public class WebSocketController {

    @MessageMapping("/hello") // 메세지에 /hello가 들어있으면 메서드 호출
    @SendTo("/topic/greetings")
    public Greeting greeting(Message msg) throws Exception {
        return new Greeting(HtmlUtils.htmlEscape(msg.getUsername() + " : " + msg.getMsg()));
    }
}
