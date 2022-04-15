package site.metacoding.greenrandomrpg.domain.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Greeting {
    // 내용을 받아서 전달해주기 위함..
    private String content;
}
