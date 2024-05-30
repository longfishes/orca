package com.longfish.orca.controller.web;


import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.vo.TemplateAbstractVO;
import com.longfish.orca.service.ITemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@RestController
@RequestMapping("/template")
@Tag(name = "模板相关")
public class TemplateController {

    @Autowired
    private ITemplateService templateService;

    @Operation(summary = "获取公共模板摘要")
    @GetMapping("/public")
    public Result<List<TemplateAbstractVO>> get() {
        return Result.success(templateService.getPublic());
    }
}
