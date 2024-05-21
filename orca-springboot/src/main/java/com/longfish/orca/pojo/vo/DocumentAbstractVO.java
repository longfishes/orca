package com.longfish.orca.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.longfish.orca.constant.CommonConstant.PATTERN;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DocumentAbstractVO implements Serializable {

    private Long id;

    private String title;

    private String cover;

    private String docAbstract;

    private String path;

    private Boolean status;

    @JsonFormat(pattern = PATTERN)
    private LocalDateTime createTime;

    @JsonFormat(pattern = PATTERN)
    private LocalDateTime updateTime;
}
