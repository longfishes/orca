package com.longfish.orca.service.impl;

import com.longfish.orca.context.BaseContext;
import com.longfish.orca.service.AIService;
import com.longfish.orca.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

import static com.longfish.orca.constant.DatabaseConstant.AI_SESSION;

@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private RedisService redisService;

    private int i = 0;

    @Override
    public String createSession() {
        UUID uuid = UUID.randomUUID();
        redisService.lPush(AI_SESSION + ":" + BaseContext.getCurrentId() + ":" + uuid, LocalDateTime.now(), 24 * 60 * 60);
        return uuid.toString();
    }

    @Override
    public List<String> listSession() {
        String prefix = AI_SESSION + ":" + BaseContext.getCurrentId();
        Set<String> keys = redisService.keys(prefix);
        String[] arr = new String[keys.size()];
        keys.forEach(k -> {
            String[] split = k.split(":");
            arr[i] = split[split.length - 1];
            i++;
        });
        i = 0;
        Arrays.sort(arr, (o1, o2) -> (int)(redisService.getExpire(prefix + ":" + o1)
                - redisService.getExpire(prefix + ":" + o2)) % 1000000007);
        return Arrays.stream(arr).toList();
    }

    @Override
    public InputStream send(String text) {
        try {
            List<String> user = Collections.singletonList("1");
            List<String> assist = new ArrayList<>();
            String baseUrl = "http://localhost:5000/chat?";
            baseUrl += build("user", user);
            baseUrl += build("assistant", assist);
            while (baseUrl.charAt(baseUrl.length() - 1) == '&')
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "text/event-stream");
            return connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String build(String paramName, List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String value : list) {
            result.append(paramName).append("=").append(URLEncoder.encode(value, StandardCharsets.UTF_8)).append("&");
        }
        return result.toString();
    }
}
