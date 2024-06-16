package com.longfish.orca.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DocumentUpdateDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "11")
    private Long id;

    private String title;

    private String cover;

    private String docAbstract;

    private String content;

    @Schema(example = "/")
    private String path;

    private Boolean isTop;

    @Schema(type = "int32", description = "0-私密 1-公开", example = "0")
    private Boolean status;
}
