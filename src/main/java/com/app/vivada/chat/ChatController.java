package com.app.vivada.chat;

import com.app.vivada.chat.dto.ChatMessage;
import com.app.vivada.chat.dto.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        ChatLocalData.usernameList.add(chatMessage.getSender());
        return chatMessage;
    }

    @GetMapping("/room/make")
    public String makeRoom(@RequestParam String username) {
        ChatLocalData.chatroomList.add(ChatRoom
                .builder()
                .maker(username)
                .roomId(UUID.randomUUID().toString().split("-")[2])
                .build());

        return "/chat/chatroomList"; // 이는 템플릿 파일의 이름입니다.
    }

    @GetMapping("/rooms")
    public String getChatRoomList(Model model) {
        model.addAttribute("chatRooms", ChatLocalData.chatroomList);
        return "/chat/chatroomList"; // 이는 템플릿 파일의 이름입니다.
    }

}
