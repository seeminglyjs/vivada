package com.app.vivada.config.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketSessionService {

    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void addSession(String sessionId, WebSocketSession session) {
        log.info(" ===== [ADD] WebSocketSessionService addSession");
        sessions.put(sessionId, session);
    }

    public void removeSession(String sessionId) {
        log.info(" ===== [REMOVE] WebSocketSessionService removeSession");
        sessions.remove(sessionId);
    }

    public WebSocketSession getSession(String sessionId) {
        log.info(" ===== [GET] WebSocketSessionService getSession");
        return sessions.get(sessionId);
    }
}
