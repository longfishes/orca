package com.longfish.orca.controller.web;

import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.dto.ContentDTO;
import com.longfish.orca.pojo.vo.*;
import com.longfish.orca.service.AIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.longfish.orca.constant.CommonConstant.*;

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

    @Operation(summary = "创建新的会话", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PostMapping("/session/create")
    public Result<AISessionVO> createSession() {
        return Result.success(AISessionVO.builder().sessionId(aiService.createSession()).build());
    }

    @Operation(summary = "获取所有会话", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @GetMapping("/sessions")
    public Result<AISessionListVO> listSession() {
        List<String> res = aiService.listSession();
        return Result.success(AISessionListVO.builder().rows(res).total((long)res.size()).build());
    }

    @Operation(summary = "智能标题", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PostMapping("/title")
    public Result<TitleVO> smartTitle(@RequestBody ContentDTO contentDTO) {
        return Result.success(TitleVO.builder().title(aiService.smartTitle(contentDTO)).build());
    }

    @Operation(summary = "智能总结", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PostMapping("/summary")
    public Result<SummaryVO> smartSummary(@RequestBody ContentDTO contentDTO) {
        return Result.success(SummaryVO.builder().summary(aiService.smartSummary(contentDTO)).build());
    }

    @Operation(summary = "文字识别", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PostMapping("/ocr")
    public Result<TextVO> ocrPredict(MultipartFile file) {
        return Result.success(TextVO.builder().text(aiService.ocrPredict(file)).build());
    }
}
