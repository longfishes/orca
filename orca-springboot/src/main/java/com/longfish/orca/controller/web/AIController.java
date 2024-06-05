package com.longfish.orca.controller.web;

import com.longfish.orca.pojo.Result;
import com.longfish.orca.service.AIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@RestController
@RequestMapping("/ai")
@Tag(name = "AI模型相关")
public class AIController {

    @Autowired
    private AIService aiService;

    @Operation(summary = "创建新的会话")
    @PostMapping("/session/create")
    public Result<String> createSession() {
        return Result.success(aiService.createSession());
    }

    @Operation(summary = "获取所有会话")
    @GetMapping("/sessions")
    public Result<List<String>> listSession() {
        return Result.success(aiService.listSession());
    }

    @Operation(summary = "向大模型发送文本")
    @PostMapping("/send")
    public Result<InputStream> send(String text) {
        return Result.success(aiService.send(text));
    }
}
