package com.longfish.orca.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "eyJhbGci.eyJleXXXXXXXX")
    private String jwt;
}
