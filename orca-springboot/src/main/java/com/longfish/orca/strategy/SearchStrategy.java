package com.longfish.orca.strategy;

import com.longfish.orca.pojo.vo.DocumentSearchVO;

import java.util.List;

public interface SearchStrategy {

    List<DocumentSearchVO> searchDocument(String keywords);

}
