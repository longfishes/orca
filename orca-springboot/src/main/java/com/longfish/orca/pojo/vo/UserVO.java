package com.longfish.orca.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.longfish.orca.constant.CommonConstant.DATE_PATTERN;
import static com.longfish.orca.constant.CommonConstant.PATTERN;

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

    @JsonFormat(pattern = DATE_PATTERN)
    private LocalDate birthday;

    private String location;

    private String ipAddress;

    private String ipSource;

    @JsonFormat(pattern = PATTERN)
    private LocalDateTime createTime;

    @JsonFormat(pattern = PATTERN)
    private LocalDateTime updateTime;

    @JsonFormat(pattern = PATTERN)
    private LocalDateTime lastLoginTime;
}
