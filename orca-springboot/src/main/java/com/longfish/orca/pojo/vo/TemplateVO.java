package com.longfish.orca.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemplateVO {

    private static final long serialVersionUID = 543151L;

    private Long id;

    private String title;

    private String cover;

    private String tempAbstract;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
