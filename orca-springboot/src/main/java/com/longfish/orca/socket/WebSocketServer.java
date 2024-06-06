package com.longfish.orca.socket;

import com.longfish.orca.config.WebSocketConfig;
import com.longfish.orca.enums.StatusCodeEnum;
import com.longfish.orca.service.RedisService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint(value = "/ws/msg/{sessionId}", configurator = WebSocketConfig.class)
@Slf4j
public class WebSocketServer {

    private static final Map<String, Session> sessionMap = new HashMap<>();

    @Autowired
    private RedisService redisService;

    @OnOpen
    public void onOpen(Session session, @PathParam("sessionId") String sessionId) throws IOException {
//        Set<String> keys = redisService.keys(AI_SESSION);
//        List<String> list = new ArrayList<>();
//        keys.forEach(k -> {
//            String[] split = k.split(":");
//            list.add(split[split.length - 1]);
//        });
//        if (!list.contains(sessionId)) {
//            session.getBasicRemote().sendText("未知的 sessionId");
//            session.close();
//            return;
//        }
        if (sessionMap.containsKey(sessionId)) {
            session.getBasicRemote().sendText(sessionId + " 已在线！");
            session.close();
            return;
        }
        String remoteIp = getHeader(session, "x-forwarded-for");
        log.info("{} AI会话 {} 建立连接", remoteIp, sessionId);
        sessionMap.put(sessionId, session);
    }

    @OnError
    public void onError(Throwable e) {
        log.error(StatusCodeEnum.WEBSOCKET_ERROR.getDesc() + "{}", e.getMessage());
        e.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, @PathParam("sessionId") String sessionId) {
        log.info("{}, {}", sessionId, message);
    }

    @OnClose
    public void onClose(@PathParam("sessionId") String sessionId) {
        sessionMap.remove(sessionId);
    }

    public static String getHeader(Session session, String headerName) throws IOException {
        String header = (String) session.getUserProperties().get(headerName);
//        if (StrUtil.isBlank(header)) {
//            log.error("未获取到客户端IP，连接不安全，即将关闭");
//            session.close();
//        }
        header = "1.1.1.1";
        return header;
    }


}
