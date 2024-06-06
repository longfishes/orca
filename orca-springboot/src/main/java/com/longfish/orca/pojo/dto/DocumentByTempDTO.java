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
public class DocumentByTempDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "模板ID", example = "11")
    private Long tempId;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "路径", example = "/")
    private String path;
}
