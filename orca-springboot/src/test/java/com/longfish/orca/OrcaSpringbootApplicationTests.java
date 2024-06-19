package com.longfish.orca;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longfish.orca.context.UploadStrategyContext;
import com.longfish.orca.enums.FilePathEnum;
import com.longfish.orca.pojo.entity.Document;
import com.longfish.orca.pojo.entity.User;
import com.longfish.orca.properties.*;
import com.longfish.orca.service.IDocumentService;
import com.longfish.orca.service.IUserService;
import com.longfish.orca.service.RedisService;
import com.longfish.orca.util.AESEncryptUtil;
import com.longfish.orca.util.CodeUtil;
import com.longfish.orca.util.IpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

import static com.longfish.orca.constant.DatabaseConstant.AI_SESSION;

@SpringBootTest
class OrcaSpringbootApplicationTests {

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RandomProperties randomProperties;

    @Autowired
    private OssConfigProperties ossConfigProperties;

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private LocalProperties localProperties;

    @Autowired
    private CodeUtil codeUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private MobileProperties mobileProperties;

    @Autowired
    private AESEncryptUtil aesEncryptUtil;

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private PageProperties pageProperties;

    @Autowired
    private SearchDisplayLengthProperties searchDisplayLengthProperties;

    @Autowired
    private RedisService redisService;

    @Test
    void testRedisGetTopic() {
        Set<String> keys = redisService.keysPrefix(AI_SESSION + ":5");
        keys.forEach(k -> {
            String[] split = k.split(":");
            System.out.println(split[split.length - 1]);
        });
    }

    @Test
    void testLogicDelete() {
        List<Document> updateList = new ArrayList<>();
        List<Document> result = documentService.lambdaQuery()
                .eq(Document::getUserId, 5)
                .in(Document::getId, Arrays.asList(1, 2, 3))
                .list();
        result.forEach(doc -> doc.setIsDeleted(true));
        result.forEach(System.out::println);
		documentService.updateBatchById(updateList);
    }

    @Test
    @SuppressWarnings("all")
    void testPage() throws NoSuchMethodException {
        Page<Document> page = Page.of(1L, 1000L);
        String s = null;
        documentService.query().orderBy(s != null, false, s).page(page);
        page.getRecords().forEach(System.out::println);
        System.out.println("总记录数：" + page.getTotal());
    }

    @Test
    void testAES() {
        String encrypt = aesEncryptUtil.encrypt("sb");
        System.out.println(aesEncryptUtil.decrypt(encrypt));
    }

    @Test
    void testUpload() {
        MultipartFile file = file2MultipartFile(new File("E:\\Administrator\\orca\\orca-springboot\\README.md"));
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.AVATAR.getPath());
        System.out.println(url);
    }

    @Test
    void testIp() {
        System.out.println(IpUtil.getIpSource("192.168.1.67"));
        System.out.println(IpUtil.getIpSource("154.64.253.77"));
        System.out.println(IpUtil.getIpSource("1.1.1.1"));
        System.out.println(IpUtil.getIpSource("182.9.32.22"));
        System.out.println(IpUtil.getIpSource("183.9.0.194"));
    }

    @Test
    void testOne() {
        User user = userService.lambdaQuery(User.builder().username("1").build()).one();
        System.out.println(user);
    }

    @Test
    void testMP() {
        userService.save(User.builder()
                .username("sb" + UUID.randomUUID().toString().substring(0, 10))
                .email("sb@qq.com")
                .password("1145")
                .createTime(LocalDateTime.now())
                .build());
        userService.save(User.builder()
                .username("sb2" + UUID.randomUUID().toString().substring(0, 10))
                .email("sb@qq.com").password("1145")
                .info("我是你爹")
                .createTime(LocalDateTime.now())
                .build());
        userService.list().forEach(System.out::println);
    }

    @Test
    void testLoadsProp() {
        System.out.println(projectProperties);
        System.out.println(jwtProperties);
        System.out.println(randomProperties);
        System.out.println(ossConfigProperties);
        System.out.println(minioProperties);
        System.out.println(localProperties);
        System.out.println(mobileProperties);
        System.out.println(pageProperties);
        System.out.println(searchDisplayLengthProperties);
    }

    @Test
    void testRand() {
        for (int i = 0; i < 200; i++) {
            System.out.println(codeUtil.getRandomCode());
        }
    }

    public MultipartFile file2MultipartFile(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        String name = file.getName();
        String originalFileName = file.getName();
        String contentType = null;
        byte[] content = new byte[0];
        try {
            contentType = Files.probeContentType(path);
            content = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MockMultipartFile(name, originalFileName, contentType, content);
    }


}
