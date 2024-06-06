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
public class TemplateUpdateDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "11")
    private Long id;

    @Schema(example = "new_title")
    private String title;

    @Schema(example = "https://static.longfish.site/tt")
    private String cover;

    @Schema(example = "ttAbs")
    private String tempAbstract;

    @Schema(example = "content")
    private String content;
}
