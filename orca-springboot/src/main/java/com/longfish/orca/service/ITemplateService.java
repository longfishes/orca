package com.longfish.orca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfish.orca.pojo.dto.TemplateDTO;
import com.longfish.orca.pojo.dto.TemplateUpdateDTO;
import com.longfish.orca.pojo.entity.Template;
import com.longfish.orca.pojo.vo.TemplateAbstractVO;
import com.longfish.orca.pojo.vo.TemplateVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
public interface ITemplateService extends IService<Template> {

    List<TemplateAbstractVO> getPublic();

    void create(TemplateDTO templateDTO);

    void updateTemp(TemplateUpdateDTO templateUpdateDTO);

    void deleteById(Long id);

    void deleteBatchIds(List<Long> ids);

    List<TemplateAbstractVO> listAll();

    List<TemplateAbstractVO> getPrivate();

    TemplateVO id(Long id);
}
