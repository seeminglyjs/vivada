package com.app.vivada.chat.dto;


import com.app.vivada.chat.enums.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChatMessage {

    private String roomId;
    private String message;
    private String sender;
    private String time; // 채팅 발송 시간
    private MessageType type;
}
