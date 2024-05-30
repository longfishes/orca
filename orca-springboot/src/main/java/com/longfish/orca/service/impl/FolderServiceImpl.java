package com.longfish.orca.service.impl;

import com.longfish.orca.context.BaseContext;
import com.longfish.orca.enums.StatusCodeEnum;
import com.longfish.orca.exception.BizException;
import com.longfish.orca.pojo.dto.FolderDTO;
import com.longfish.orca.pojo.dto.FolderUpdateDTO;
import com.longfish.orca.pojo.entity.Folder;
import com.longfish.orca.mapper.FolderMapper;
import com.longfish.orca.service.IFolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author longfish
 * @since 2024-05-21
 */
@Service
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder> implements IFolderService {

    @Override
    public List<Folder> root() {
        return lambdaQuery()
                .eq(Folder::getUserId, BaseContext.getCurrentId())
                .eq(Folder::getPath, "/")
                .list();
    }

    @Override
    public void deleteById(Long id) {
        Folder result = getById(id);
        if (result == null || !result.getUserId().equals(BaseContext.getCurrentId())) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        removeById(id);
    }

    @Override
    public void deleteBatchIds(List<Long> ids) {
        List<Folder> result = listByIds(ids);
        Long currentId = BaseContext.getCurrentId();
        if (result == null) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        result.forEach(r -> {
            if (!r.getUserId().equals(currentId)) {
                throw new BizException(StatusCodeEnum.FORBIDDEN);
            }
        });
        removeBatchByIds(ids);
    }

    @Override
    public void create(FolderDTO folderDTO) {
        String path = folderDTO.getPath();
        while (path.charAt(path.length() - 1) == '/') {
            path = path.substring(0, path.length() - 1);
        }
        save(Folder.builder()
                .userId(BaseContext.getCurrentId())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .path(path)
                .build());
    }

    @Override
    public void updateName(FolderUpdateDTO folderUpdateDTO) {
        Folder result = getById(folderUpdateDTO.getId());
        if (result == null || !result.getUserId().equals(BaseContext.getCurrentId())) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        if (folderUpdateDTO.getName().contains("/")) {
            throw new BizException("文件名不合法");
        }
        String[] split = result.getPath().split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            sb.append("/");
            sb.append(split[i]);
        }
        sb.append(folderUpdateDTO.getName());
        result.setPath(sb.toString());
        result.setUpdateTime(LocalDateTime.now());
        updateById(result);
    }
}
