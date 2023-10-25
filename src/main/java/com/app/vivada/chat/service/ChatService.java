package com.app.vivada.chat.service;

import com.app.vivada.chat.dto.ChatRoom;

import java.util.List;

public interface ChatService {

    List<ChatRoom> findAllRoom();

    ChatRoom createRoom(String name);

    ChatRoom findById(String roomId);
}
