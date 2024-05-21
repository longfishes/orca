package com.longfish.orca.pojo.dto;

import lombok.Data;

import java.io.Serializable;

import static com.longfish.orca.constant.PageConstant.*;

@Data
public class PageDTO implements Serializable {

    private Long pageNo = DEFAULT_PAGE_NO;

    private Long pageSize = DEFAULT_PAGE_SIZE;

    private String sortBy = DEFAULT_SORTED_BY;

    private Boolean isAsc = DEFAULT_IS_ASC;

}
