package com.longfish.orca.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DocumentUpdateDTO {

    private Long id;

    private String title;

    private String cover;

    private String docAbstract;

    private String content;

    private String path;

    private Boolean isTop;

    private Boolean isLocked;

    private Boolean status;
}
