package com.longfish.orca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longfish.orca.pojo.entity.History;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author longfish
 * @since 2024-06-05
 */
public interface HistoryMapper extends BaseMapper<History> {

    List<History> recent(Long limit);
}
