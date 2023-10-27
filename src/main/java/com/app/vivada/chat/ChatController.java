package com.app.vivada.chat;

import com.app.vivada.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/room/enter")
    public void enter(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        message.setMessage(message.getSender()+"님이 입장하였습니다.");
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }

    @MessageMapping("/chat/message")
    public void chat(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }

    @MessageMapping("chat/room/leave")
    public void chatRoomLeave(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws IOException {
        message.setMessage(message.getSender()+"님이 퇴장하셨습니다.");
        // WebSocket 세션을 종료할 세션 아이디 가져오기
        String sessionId = headerAccessor.getSessionId();

        /*
         * TODO 세션을 제거하거나 토큰 기반으로 세션제거 구현 코드 필요
         *
         */

        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
}
