package com.longfish.orca.service.impl;

import com.longfish.orca.context.BaseContext;
import com.longfish.orca.pojo.dto.ContentDTO;
import com.longfish.orca.properties.PyProperties;
import com.longfish.orca.service.AIService;
import com.longfish.orca.service.RedisService;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.longfish.orca.constant.CommonConstant.APPLICATION_JSON;
import static com.longfish.orca.constant.DatabaseConstant.AI_SESSION;

@Service("aiServiceImpl")
public class AIServiceImpl implements AIService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private PyProperties pyProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OkHttpClient okHttpClient;

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
        Arrays.sort(arr, (o1, o2) -> (int) (redisService.getExpire(prefix + ":" + o1)
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

    @SneakyThrows
    @Override
    public String ocrPredict(MultipartFile file) {
        String img = Base64.getEncoder().encodeToString(file.getBytes());

        Response response = okHttpClient.newCall(
                new Request.Builder()
                        .url(pyProperties.getOcrUrl() + pyProperties.getOcrPath())
                        .method("POST", RequestBody.create(MediaType.parse(APPLICATION_JSON),
                                "{\"images\": [\" " + img + " \"]}"))
                        .header(pyProperties.getHeaderName(), pyProperties.getAccessKey())
                        .build()).execute();

        return text(Objects.requireNonNull(response.body()).string());
    }

    public static String text(String jsonString) {
        StringBuilder texts = new StringBuilder();
        Pattern pattern = Pattern.compile("\"text\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(jsonString);
        while (matcher.find()) {
            texts.append(matcher.group(1));
        }
        return texts.toString();
    }

}
