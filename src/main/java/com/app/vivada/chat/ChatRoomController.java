package com.app.vivada.chat;

import com.app.vivada.chat.dto.ChatRoom;
import com.app.vivada.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {//https://velog.io/@rainbowweb/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-STOMP%EB%A1%9C-%EC%B1%84%ED%8C%85%EB%B0%A9-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0

    private final ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(
//            @Payload ChatMessage chatMessage
//    ) {
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public String addUser(
//            @Payload ChatMessage chatMessage,
//            SimpMessageHeaderAccessor headerAccessor
//    ) {
//        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        headerAccessor.getSessionAttributes().put("roomId", chatMessage.getRoomId());
//        ChatLocalData.usernameList.add(chatMessage.getSender());
//
//        return "/chat/chatroomList";
//    }



}
