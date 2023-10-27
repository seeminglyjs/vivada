package com.app.vivada.chat.service;

import com.app.vivada.chat.dto.ChatRoom;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    
    //DB 사용시 제거 가능
    private Map<String, ChatRoom> chatRooms;

    //DB 사용시 제거 가능
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    @Override
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    @Override
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    @Override
    public ChatRoom makeRoom(String name) {
        ChatRoom chatRoom = ChatRoom.make(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
