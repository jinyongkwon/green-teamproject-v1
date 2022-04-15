package site.metacoding.greenrandomrpg.domain.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

    private String username;
    // 메세지를 받음.
    private String msg;
}
