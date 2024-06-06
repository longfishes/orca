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
public class FolderUpdateDTO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "11")
    private Long id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "new_name")
    private String name;
}
