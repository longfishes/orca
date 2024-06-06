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
public class EmailBindDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "longfishes@qq.com")
    private String email;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "114514")
    private String code;
}
