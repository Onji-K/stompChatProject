package com.example.stompChat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class MessageBrokerConfig implements WebSocketMessageBrokerConfigurer {
    // WebSocketMessageBrokerConfigurer interface 구현
    // 아래의 두 메서드가 핵심이다.

    // 클라이언트에서 SockJS를 통해 접속할 주소를 설정한다.
    // ex) var sock = new SockJS("stompTest")
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompTest") //SockJS 접속 주소
                .setAllowedOriginPatterns("*") //모든 곳에서 접속허용
                .withSockJS(); //SockJS 사용, stomp 는 sockJs 기반
    }

    //messageBroker 를 활성화 시키고 설정한다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //simpleBroker 를 활성화 시키고, 해당 접두사 목적지를 필터링 함. 여러개 가능
        registry.enableSimpleBroker("/topic");
        //전송의 접두사를 붙여줌, 향후 컨트롤러 매핑시 이 접두사를 붙이지 않아야 됨.
        registry.setApplicationDestinationPrefixes("/chat");
    }




}
