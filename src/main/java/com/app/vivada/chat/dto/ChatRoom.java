package com.app.vivada.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChatRoom {
    private String roomId;
    private String maker;
}
