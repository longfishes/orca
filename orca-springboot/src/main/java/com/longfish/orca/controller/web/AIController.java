package com.longfish.orca.controller.web;

import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.dto.ContentDTO;
import com.longfish.orca.pojo.vo.AISessionListVO;
import com.longfish.orca.pojo.vo.AISessionVO;
import com.longfish.orca.pojo.vo.TitleVO;
import com.longfish.orca.service.AIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@RestController("aiController")
@RequestMapping("/ai")
@Tag(name = "AI模型相关")
@Slf4j
public class AIController {

    @Autowired
    private AIService aiService;

    @Operation(summary = "创建新的会话")
    @PostMapping("/session/create")
    public Result<AISessionVO> createSession() {
        return Result.success(AISessionVO.builder().sessionId(aiService.createSession()).build());
    }

    @Operation(summary = "获取所有会话")
    @GetMapping("/sessions")
    public Result<AISessionListVO> listSession() {
        List<String> res = aiService.listSession();
        return Result.success(AISessionListVO.builder().rows(res).total((long)res.size()).build());
    }

    @Operation(summary = "智能标题")
    @PostMapping("/title")
    public Result<TitleVO> smartTitle(@RequestBody ContentDTO contentDTO) {
        return Result.success(TitleVO.builder().title(aiService.smartTitle(contentDTO)).build());
    }
}
