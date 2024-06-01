package com.longfish.orca.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.longfish.orca.context.BaseContext;
import com.longfish.orca.enums.StatusCodeEnum;
import com.longfish.orca.exception.BizException;
import com.longfish.orca.pojo.dto.TemplateDTO;
import com.longfish.orca.pojo.dto.TemplateUpdateDTO;
import com.longfish.orca.pojo.entity.Template;
import com.longfish.orca.mapper.TemplateMapper;
import com.longfish.orca.pojo.vo.TemplateAbstractVO;
import com.longfish.orca.pojo.vo.TemplateVO;
import com.longfish.orca.service.ITemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

    @Override
    public List<TemplateAbstractVO> getPublic() {
        List<Template> result = lambdaQuery().eq(Template::getUserId, -1).list();
        List<TemplateAbstractVO> ret = new ArrayList<>();
        if (result != null) {
            result.forEach(r -> ret.add(BeanUtil.copyProperties(r, TemplateAbstractVO.class)));
        }
        return ret;
    }

    @Override
    public List<TemplateAbstractVO> listAll() {
        List<Template> result = lambdaQuery()
                .eq(Template::getUserId, -1)
                .or()
                .eq(Template::getUserId, BaseContext.getCurrentId())
                .list();
        List<TemplateAbstractVO> ret = new ArrayList<>();
        if (result != null) {
            result.forEach(r -> ret.add(BeanUtil.copyProperties(r, TemplateAbstractVO.class)));
        }
        return ret;
    }

    @Override
    public List<TemplateAbstractVO> getPrivate() {
        List<Template> result = lambdaQuery()
                .eq(Template::getUserId, BaseContext.getCurrentId())
                .list();
        List<TemplateAbstractVO> ret = new ArrayList<>();
        if (result != null) {
            result.forEach(r -> ret.add(BeanUtil.copyProperties(r, TemplateAbstractVO.class)));
        }
        return ret;
    }

    @Override
    public TemplateVO id(Long id) {
        Template result = getById(id);
        if (result == null || !result.getUserId().equals(BaseContext.getCurrentId())) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        return BeanUtil.copyProperties(result, TemplateVO.class);
    }

    @Override
    public void create(TemplateDTO templateDTO) {
        Template save = BeanUtil.copyProperties(templateDTO, Template.class);
        save.setUserId(BaseContext.getCurrentId());
        save.setCreateTime(LocalDateTime.now());
        save.setUpdateTime(LocalDateTime.now());
        save(save);
    }

    @Override
    public void updateTemp(TemplateUpdateDTO templateUpdateDTO) {
        Template result = getById(templateUpdateDTO.getId());
        Long currentId = BaseContext.getCurrentId();
        if (result == null || !result.getUserId().equals(currentId)) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        Template update = BeanUtil.copyProperties(templateUpdateDTO, Template.class);
        if (update.getContent().equals("")) {
            update.setContent(null);
        }
        if (update.getCover().equals("")) {
            update.setCover(null);
        }
        if (update.getTempAbstract().equals("")) {
            update.setTempAbstract(null);
        }
        if (update.getTitle().equals("")) {
            update.setTitle(null);
        }
        update.setUserId(currentId);
        update.setUpdateTime(LocalDateTime.now());
        updateById(update);
    }

    @Override
    public void deleteById(Long id) {
        Template result = getById(id);
        if (result == null || !result.getUserId().equals(BaseContext.getCurrentId())) {
            throw new BizException(StatusCodeEnum.FORBIDDEN);
        }
        removeById(id);
    }

    @Override
    public void deleteBatchIds(List<Long> ids) {
        List<Template> result = lambdaQuery()
                .eq(Template::getUserId, BaseContext.getCurrentId())
                .in(Template::getId, ids)
                .list();
        removeBatchByIds(result);
    }
}
