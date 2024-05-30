package com.longfish.orca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfish.orca.pojo.dto.DocumentDTO;
import com.longfish.orca.pojo.dto.DocumentUpdateDTO;
import com.longfish.orca.pojo.dto.PageDTO;
import com.longfish.orca.pojo.entity.Document;
import com.longfish.orca.pojo.vo.DocumentAbstractVO;
import com.longfish.orca.pojo.vo.DocumentVO;
import com.longfish.orca.pojo.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
public interface IDocumentService extends IService<Document> {

    void create(DocumentDTO documentDTO);

    PageVO<DocumentAbstractVO> listAll(PageDTO pageDTO);

    List<DocumentAbstractVO> top();

    List<DocumentAbstractVO> trash();

    DocumentVO id(Long id);

    void deleteById(Long id);

    void deleteByIdBatch(List<Long> ids);

    void deleteByIdTruly(Long id);

    void deleteByIdBatchTruly(List<Long> ids);

    void restore(Long id);

    void restoreBatch(List<Long> ids);

    List<DocumentAbstractVO> path(String path);

    void updateDoc(DocumentUpdateDTO documentUpdateDTO);
}
