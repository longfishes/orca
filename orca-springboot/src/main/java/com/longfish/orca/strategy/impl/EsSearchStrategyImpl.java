package com.longfish.orca.strategy.impl;

import com.longfish.orca.pojo.vo.DocumentSearchVO;
import com.longfish.orca.strategy.SearchStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("esSearchStrategyImpl")
public class EsSearchStrategyImpl implements SearchStrategy {

    @Override
    public List<DocumentSearchVO> searchDocument(String keywords) {
        return null;
    }
}
