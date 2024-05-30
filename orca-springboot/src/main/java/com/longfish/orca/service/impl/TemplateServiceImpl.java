package com.longfish.orca.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.longfish.orca.pojo.entity.Template;
import com.longfish.orca.mapper.TemplateMapper;
import com.longfish.orca.pojo.vo.TemplateAbstractVO;
import com.longfish.orca.service.ITemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
