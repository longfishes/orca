package com.longfish.orca.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.longfish.orca.constant.CommonConstant.PATTERN;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemplateVO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "11")
    private Long id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "title")
    private String title;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "https://static.longfish.site/tt")
    private String cover;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ttAbs")
    private String tempAbstract;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "content")
    private String content;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "2000-01-01 00:00:00")
    @JsonFormat(pattern = PATTERN)
    private LocalDateTime createTime;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "2000-01-01 00:00:00")
    @JsonFormat(pattern = PATTERN)
    private LocalDateTime updateTime;
}
