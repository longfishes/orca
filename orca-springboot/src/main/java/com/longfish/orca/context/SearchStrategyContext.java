package com.longfish.orca.context;

import com.longfish.orca.pojo.vo.DocumentSearchVO;
import com.longfish.orca.stategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.longfish.orca.enums.SearchModeEnum.getStrategy;

@Service
public class SearchStrategyContext {

    @Value("${search.mode}")
    private String searchMode;

    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;

    public List<DocumentSearchVO> executeSearchStrategy(String keywords) {
        return searchStrategyMap.get(getStrategy(searchMode)).searchArticle(keywords);
    }

}
