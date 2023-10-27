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

    @GetMapping("/room")
    public String getRoomList(Model model) {
        /*
         * TODO 접속 유저에 따라 방 정보를 구분할 필요 있음
         *  파라미터로 전달 받은 정보 기준 ex) session | token | 인증객체
         */
        return "/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> getRoomListResponseBody() {
        /*
         * TODO 접속 유저에 따라 방 정보를 구분할 필요 있음
         *  파라미터로 전달 받은 정보 기준 ex) session | token | 인증객체
         */
        return chatService.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom makeRoom(@RequestParam String name) {
        /*
         * TODO 요청 유저 정보를 별개의 파라미터로 받아 처리해야함
         */
        return chatService.makeRoom(name);
    }

    @GetMapping("/room/enter/{roomId}")
    public String enterRoom(Model model, @PathVariable String roomId) {
        /*
         * TODO 요청 유저 정보를 별개의 파라미터로 받아 처리해야함
         *  방에 따른 유저 권한 체크
         */
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        /*
         * TODO 요청 유저 정보를 별개의 파라미터로 받아 처리해야함
         */
        return chatService.findById(roomId);
    }
}
