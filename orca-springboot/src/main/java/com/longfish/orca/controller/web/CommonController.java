package com.longfish.orca.controller.web;

import com.longfish.orca.context.UploadStrategyContext;
import com.longfish.orca.enums.FilePathEnum;
import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.vo.UrlVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.longfish.orca.constant.CommonConstant.*;

@Tag(name = "通用接口")
@RestController
public class CommonController {

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Operation(summary = "上传文档图片", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PostMapping("/document/img/upload")
    public Result<UrlVO> uploadDocImg(MultipartFile file) {
        return Result.success(UrlVO.builder()
                .url(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.DOCUMENT.getPath()))
                .build());
    }

    @Operation(summary = "上传头像图片", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PostMapping("/user/avatar/upload")
    public Result<UrlVO> uploadAvatar(MultipartFile file) {
        return Result.success(UrlVO.builder()
                .url(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.AVATAR.getPath()))
                .build());
    }
}
