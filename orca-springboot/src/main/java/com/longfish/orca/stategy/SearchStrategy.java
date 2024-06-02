package com.longfish.orca.stategy;

import com.longfish.orca.pojo.vo.DocumentSearchVO;

import java.util.List;

public interface SearchStrategy {

    List<DocumentSearchVO> searchArticle(String keywords);

}
