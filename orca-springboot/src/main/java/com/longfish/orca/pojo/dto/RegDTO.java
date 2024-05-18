package com.longfish.orca.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegDTO {

    private String username;

    private String emailOrPhone;

    private String code;

    private String password;
}
