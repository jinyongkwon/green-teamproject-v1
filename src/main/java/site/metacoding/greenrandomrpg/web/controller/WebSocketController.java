package site.metacoding.greenrandomrpg.web.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

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
        String filterMsg = abuseFilter(msg.getMsg());
        return new Greeting(HtmlUtils.htmlEscape(msg.getUsername() + " : " + filterMsg));
    }

    public String abuseFilter(String msg) { // 욕설 필터링.
        try {
            BufferedReader br = new BufferedReader( // 욕설 리스트파일을 버퍼에 담음.
                    new InputStreamReader(new FileInputStream("src/main/resources/static/text/abuseList.txt"),
                            "UTF-8"));
            String abuse = br.readLine(); // 버퍼에 있는것을 String에 담음
            List<String> abuseList = Arrays.asList(abuse.split(",")); // ,로 구분되어있으므로 파싱해서 List에 담음.
            for (String abuseParse : abuseList) { // 리스트 사이즈만큼 for-eache
                if (msg.contains(abuseParse)) { // 만약 욕설이 있으면
                    String Parse = "";
                    for (int i = 0; i < abuseParse.length(); i++) { // 욕설의 길이만큼 String에 *담음.
                        Parse += "*";
                    }
                    msg = msg.replaceAll(abuseParse, Parse); // 받은 메세지의 욕설부분을 *로 변환.
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("파일을 찾지 못했습니다.");
        }
        return msg; // 바뀐 메세지 반환.
    }
}
