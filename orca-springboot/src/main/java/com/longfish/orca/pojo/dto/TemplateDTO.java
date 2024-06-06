package com.longfish.orca.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemplateDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "title")
    private String title;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "https://static.longfish.site")
    private String cover;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ttAbs")
    private String tempAbstract;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "content")
    private String content;
}
