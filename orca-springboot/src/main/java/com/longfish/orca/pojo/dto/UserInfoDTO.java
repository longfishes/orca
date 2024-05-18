package com.longfish.orca.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserInfoDTO {

    private String nickname;

    private String avatar;

    private String info;

    private Integer gender;

    private String birthday;

    private String location;
}
