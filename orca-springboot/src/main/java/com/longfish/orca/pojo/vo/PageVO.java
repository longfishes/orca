package com.longfish.orca.pojo.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PageVO<T> implements Serializable {

    private Long total;

    private List<T> rows;

    public PageVO(Page<T> page) {
        rows = page.getRecords();
        total = page.getTotal();
    }

    public PageVO(List<T> records) {
        rows = records;
        total = (long)records.size();
    }
}
