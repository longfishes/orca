package com.longfish.orca.service;

import com.longfish.orca.pojo.dto.FolderDTO;
import com.longfish.orca.pojo.dto.FolderUpdateDTO;
import com.longfish.orca.pojo.entity.Folder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author longfish
 * @since 2024-05-21
 */
public interface IFolderService extends IService<Folder> {

    void create(FolderDTO folderDTO);

    void updateName(FolderUpdateDTO folderUpdateDTO);
}
