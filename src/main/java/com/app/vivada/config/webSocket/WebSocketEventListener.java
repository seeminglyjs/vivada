package com.app.vivada.config.webSocket;

import com.app.vivada.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations sendingOperations;


    /*
     * 유저가 채팅방을 나갔을 경우 동작하는 이벤트 핸들러
     * @param event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        if (username != null && roomId != null) {
            log.info("User disconnected:  {}", username + "  roomId ->" + roomId);

            sendingOperations.convertAndSend("/topic/chat/room/" + roomId,
                    ChatMessage
                            .builder()
                            .sender(username)
                            .roomId(roomId)
                            .message(username + "님이 방에서 나가셨습니다.")
                            .build());
        }else{
            log.info(" ==== [ERROR] username | roomId is null ");
        }
    }
}
