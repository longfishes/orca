package com.longfish.orca.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.longfish.orca.constant.CommonConstant.PATTERN;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DocumentVO {

    private Long id;

    private String title;

    private String cover;

    private String docAbstract;

    private String content;

    private String path;

    private Boolean isTop;

    private Boolean isDeleted;

    private Boolean status;

    @JsonFormat(pattern = PATTERN)
    private LocalDateTime createTime;

    @JsonFormat(pattern = PATTERN)
    private LocalDateTime updateTime;
}