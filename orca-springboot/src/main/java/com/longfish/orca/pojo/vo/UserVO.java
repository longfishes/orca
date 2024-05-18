package com.longfish.orca.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserVO {

    private String username;

    private String email;

    private String phone;

    private String nickname;

    private String avatar;

    private String info;

    private Integer gender;

    private LocalDate birthday;

    private String location;

    private String ipAddress;

    private String ipSource;

    private String createTime;

    private String updateTime;

    private String lastLoginTime;
}
