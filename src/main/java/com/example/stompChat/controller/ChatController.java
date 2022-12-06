package com.example.stompChat.controller;

import com.example.stompChat.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    //첫번째 방법
    @MessageMapping("/TTT") // "/chat/TTT"로 메시지가 오면 여기로 매핑
    @SendTo("/topic/message") // 여기 구독자들한테 보내라
    public MessageDto chatTTT(@Payload MessageDto message){
        /*
        Client 에서 JSON 을 String 으로 바꿔서 쏴주면,
        @Payload 에 있는 객체에 매핑해줌, 어노테이션 없어도 인자 하나면 매핑은 됨
         */
        log.info("TTT message >>> " + message.getMessage());
        log.info("TTT userName >>> " + message.getUserName());

        return message; //똑같이 JSON 으로 쏴줌
    }

    //두번째 방법
    //이 방법의 경우 경로를 직접 만들기도 가능
    //org.springframework.messaging.simp 에는 여러가지 stomp 와 관련된 기능들이 들어가 있다.
    private final SimpMessageSendingOperations simpMessageSendingOperations; //org.springframework.messaging.simp

    @MessageMapping("/SSS")
    public void chatSSS(@Payload MessageDto message ,SimpMessageHeaderAccessor headerAccessor){
        log.info("SSS message >>> " + message.getMessage());
        log.info("SSS userName >>> " + message.getUserName());
        simpMessageSendingOperations.convertAndSend("topic/message", message);
    }
}
