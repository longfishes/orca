package com.longfish.orca.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConditionDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "关键词", example = "你好")
    private String keywords;
}
