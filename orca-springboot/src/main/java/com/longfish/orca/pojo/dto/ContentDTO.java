package com.longfish.orca.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContentDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "tt_content")
    private String content;
}
