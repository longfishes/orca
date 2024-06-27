package com.longfish.orca.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfish.orca.constant.DatabaseConstant;
import com.longfish.orca.context.BaseContext;
import com.longfish.orca.enums.StatusCodeEnum;
import com.longfish.orca.exception.BizException;
import com.longfish.orca.mapper.DocumentMapper;
import com.longfish.orca.mapper.HistoryMapper;
import com.longfish.orca.pojo.dto.DocumentByTempDTO;
import com.longfish.orca.pojo.dto.DocumentDTO;
import com.longfish.orca.pojo.dto.DocumentUpdateDTO;
import com.longfish.orca.pojo.dto.PageDTO;
import com.longfish.orca.pojo.entity.Document;
import com.longfish.orca.pojo.entity.Folder;
import com.longfish.orca.pojo.entity.History;
import com.longfish.orca.pojo.entity.Template;
import com.longfish.orca.pojo.vo.DocumentAbstractVO;
import com.longfish.orca.pojo.vo.DocumentVO;
import com.longfish.orca.pojo.vo.PageVO;
import com.longfish.orca.service.IDocumentService;
import com.longfish.orca.service.IFolderService;
import com.longfish.orca.service.IHistoryService;
import com.longfish.orca.service.ITemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@Service
@Slf4j
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements IDocumentService {

    @Autowired
    private IFolderService folderService;

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private HistoryMapper historyMapper;

    @Transactional
    @Override
    public void create(DocumentDTO documentDTO) {
        Long currentId = BaseContext.getCurrentId();
        if (StringUtils.isBlank(documentDTO.getPath())) documentDTO.setPath("/");
        Document save = BeanUtil.copyProperties(documentDTO, Document.class);
        save.setUserId(currentId);
        save.setCreateTime(LocalDateTime.now());
        save.setUpdateTime(LocalDateTime.now());
        save(save);

        if (!folderService.lambdaQuery().eq(Folder::getUserId, currentId).exists()) {
            folderService.save(Folder.builder()
                    .userId(currentId)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }
    }

    @Override
    public void createByTemp(DocumentByTempDTO documentByTempDTO) {
        Template result = templateService.getById(documentByTempDTO.getTempId());
        if (result == null || !result.getUserId().equals(BaseContext.getCurrentId())) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        DocumentDTO create = BeanUtil.copyProperties(result, DocumentDTO.class);
        create.setPath(documentByTempDTO.getPath());
        create(create);
    }

    @Override
    public List<DocumentAbstractVO> recent(Long limit) {
        if (limit == null || limit <= 0) limit = 1000L;
        List<History> histories = historyMapper.recent(limit);
        if (histories == null) {
            return new ArrayList<>();
        }
        List<Long> docIds = histories.stream().map(History::getDocId).toList();
        List<DocumentAbstractVO> resultList = new ArrayList<>();
        docIds.forEach(i -> {
            Document doc = getById(i);
            if (!doc.getIsLocked() && !doc.getIsDeleted()) {
                resultList.add(BeanUtil.copyProperties(doc, DocumentAbstractVO.class));
            }
        });
        return resultList;
    }

    @Override
    public PageVO<DocumentAbstractVO> listAll(PageDTO pageDTO) {
        Page<Document> page = Page.of(pageDTO.getPageNo(), pageDTO.getPageSize());
        String sortBy = pageDTO.getSortBy();
        try {
            query().eq(DatabaseConstant.USER_ID, BaseContext.getCurrentId())
                    .eq(DatabaseConstant.IS_DELETED, 0)
                    .eq(DatabaseConstant.IS_TOP, 0)
                    .eq(DatabaseConstant.IS_LOCKED, 0)
                    .orderBy(sortBy != null && !sortBy.equals(""), pageDTO.getIsAsc(), sortBy)
                    .page(page);
        } catch (Exception e) {
            throw new BizException("未知排序字段");
        }
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        page.getRecords().forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return new PageVO<>(returnList);
    }

    @Override
    public List<DocumentAbstractVO> top() {
        List<Document> result = lambdaQuery().eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getIsLocked, 0)
                .eq(Document::getIsTop, 1)
                .orderBy(true, false, Document::getUpdateTime)
                .list();
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        result.forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return returnList;
    }

    @Override
    public List<DocumentAbstractVO> trash() {
        List<Document> result = lambdaQuery().eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .orderBy(true, false, Document::getUpdateTime)
                .list();
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        result.forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return returnList;
    }

    @Override
    public List<DocumentAbstractVO> path(String path) {
        if (path == null || path.equals("")) path = "/";
        List<Document> result = lambdaQuery().eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getIsLocked, 0)
                .eq(Document::getPath, path)
                .orderBy(true, false, Document::getUpdateTime)
                .list();
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        result.forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return returnList;
    }

    @Override
    public void updateDoc(DocumentUpdateDTO documentUpdateDTO) {
        Document result = getById(documentUpdateDTO.getId());
        if (!result.getUserId().equals(BaseContext.getCurrentId()) || result.getIsLocked()) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        Document update = BeanUtil.copyProperties(documentUpdateDTO, Document.class);

        if (StringUtils.isBlank(update.getTitle())) {
            update.setTitle(null);
        }
        if (StringUtils.isBlank(update.getCover())) {
            update.setCover(null);
        }
        if (StringUtils.isBlank(update.getDocAbstract())) {
            update.setDocAbstract(null);
        }
        if (StringUtils.isBlank(update.getContent())) {
            update.setContent(null);
        }
        if (StringUtils.isBlank(update.getPath())) {
            update.setPath(null);
        }
        update.setUpdateTime(LocalDateTime.now());
        updateById(update);
    }

    @Override
    public DocumentVO id(Long id) {
        Long currentId = BaseContext.getCurrentId();
        Document result = lambdaQuery()
                .eq(Document::getId, id)
                .eq(Document::getIsLocked, 0)
                .eq(Document::getUserId, currentId)
                .one();
        if (result != null) {
            historyService.save(History.builder()
                    .userId(currentId)
                    .docId(result.getId())
                    .createTime(LocalDateTime.now())
                    .build());
            return BeanUtil.copyProperties(result, DocumentVO.class).setEditable(true);
        }
        Document result2 = lambdaQuery()
                .eq(Document::getId, id)
                .eq(Document::getIsLocked, 0)
                .eq(Document::getStatus, 1)
                .one();
        if (result2 != null) {
            historyService.save(History.builder()
                    .userId(currentId)
                    .docId(result2.getId())
                    .createTime(LocalDateTime.now())
                    .build());
            return BeanUtil.copyProperties(result2, DocumentVO.class).setEditable(false);
        }
        throw new BizException(StatusCodeEnum.FORBIDDEN);
    }

    @Override
    public void deleteById(Long id) {
        lambdaUpdate().set(Document::getIsDeleted, 1)
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getId, id)
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getIsLocked, 0)
                .update();
    }

    @Override
    public void deleteByIdBatch(List<Long> ids) {
        Long currentId = BaseContext.getCurrentId();
        List<Document> result = lambdaQuery()
                .eq(Document::getUserId, currentId)
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getIsLocked, 0)
                .in(Document::getId, ids)
                .list();
        result.forEach(doc -> doc.setIsDeleted(true));
        updateBatchById(result);
    }

    @Override
    public void deleteByIdTruly(Long id) {
        Document remove = lambdaQuery()
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getId, id)
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .one();
        removeById(remove);
    }

    @Override
    public void deleteByIdBatchTruly(List<Long> ids) {
        List<Document> result = lambdaQuery()
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .in(Document::getId, ids)
                .list();
        removeBatchByIds(result);
    }

    @Override
    public void restore(Long id) {
        lambdaUpdate().set(Document::getIsDeleted, 0)
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getId, id)
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .update();
    }

    @Override
    public void restoreBatch(List<Long> ids) {
        Long currentId = BaseContext.getCurrentId();
        List<Document> result = lambdaQuery()
                .eq(Document::getUserId, currentId)
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .in(Document::getId, ids)
                .list();
        result.forEach(doc -> doc.setIsDeleted(false));
        updateBatchById(result);
    }
}
