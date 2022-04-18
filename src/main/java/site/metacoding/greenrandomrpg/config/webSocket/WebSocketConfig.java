package site.metacoding.greenrandomrpg.config.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // message처리를 하기위한 어노테이션
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {// message처리를 위한 설정.

    // 메세지 브로커 구성을 위한 메서드
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // /topic이 붙은 클라이언트로 메시지를 다시 전달.
        config.enableSimpleBroker("/topic");
        // /app이 붙은 메세지가 오면 GreetingController.Greeting()메서드 호출.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket사용이 불가할경우 SockJs 옵션 활성화
        // /gs-guide-websocket에 연결을 시도
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}
