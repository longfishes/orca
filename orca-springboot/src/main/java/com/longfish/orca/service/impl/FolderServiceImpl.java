package com.longfish.orca.service.impl;

import com.longfish.orca.context.BaseContext;
import com.longfish.orca.pojo.dto.FolderDTO;
import com.longfish.orca.pojo.dto.FolderUpdateDTO;
import com.longfish.orca.pojo.entity.Folder;
import com.longfish.orca.mapper.FolderMapper;
import com.longfish.orca.service.IFolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public void create(FolderDTO folderDTO) {
        save(Folder.builder()
                .userId(BaseContext.getCurrentId())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .path(folderDTO.getPath())
                .build());
    }

    @Override
    public void updateName(FolderUpdateDTO folderUpdateDTO) {
        Folder result = getById(folderUpdateDTO.getId());
//        if (result.get)
    }
}
