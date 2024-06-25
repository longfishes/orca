package com.longfish.orca.service.impl;

import com.longfish.orca.context.BaseContext;
import com.longfish.orca.pojo.dto.ContentDTO;
import com.longfish.orca.properties.PyProperties;
import com.longfish.orca.service.AIService;
import com.longfish.orca.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.longfish.orca.constant.DatabaseConstant.AI_SESSION;

@Service("aiServiceImpl")
public class AIServiceImpl implements AIService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private PyProperties pyProperties;

    @Autowired
    private RestTemplate restTemplate;

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
        Set<String> keys = redisService.keysPrefix(prefix);
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
    public String smartTitle(ContentDTO contentDTO) {
        return restTemplate.postForEntity(
                pyProperties.getBaseUrl() + "/title",
                contentDTO,
                String.class).getBody();
    }

    @Override
    public String smartSummary(ContentDTO contentDTO) {
        return restTemplate.postForEntity(
                pyProperties.getBaseUrl() + "/summary",
                contentDTO,
                String.class).getBody();
    }

    @Override
    public String ocrPredict(MultipartFile file) {
        return null;
    }

}
