package com.longfish.orca.stategy.impl;

import com.longfish.orca.pojo.vo.DocumentSearchVO;
import com.longfish.orca.stategy.SearchStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("esSearchStrategyImpl")
public class EsSearchStrategyImpl implements SearchStrategy {

    @Override
    public List<DocumentSearchVO> searchArticle(String keywords) {
        return null;
    }
}
