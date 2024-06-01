package com.longfish.orca.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemplateUpdateDTO {

    private Long id;

    private String title;

    private String cover;

    private String tempAbstract;

    private String content;
}
