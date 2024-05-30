package com.longfish.orca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfish.orca.pojo.entity.Template;
import com.longfish.orca.pojo.vo.TemplateAbstractVO;

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
}
