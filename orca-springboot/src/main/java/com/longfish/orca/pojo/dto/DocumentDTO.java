package com.longfish.orca.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DocumentDTO {

    private String title;

    private String cover;

    private String docAbstract;

    private String content;

    private String path;
}
