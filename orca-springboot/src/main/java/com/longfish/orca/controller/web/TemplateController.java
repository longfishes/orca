package com.longfish.orca.controller.web;


import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.dto.TemplateDTO;
import com.longfish.orca.pojo.dto.TemplateUpdateDTO;
import com.longfish.orca.pojo.vo.TemplateAbstractVO;
import com.longfish.orca.pojo.vo.TemplateVO;
import com.longfish.orca.service.ITemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.longfish.orca.constant.CommonConstant.*;

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

    @Operation(summary = "获取公共模板摘要", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @GetMapping("/public")
    public Result<List<TemplateAbstractVO>> getPublic() {
        return Result.success(templateService.getPublic());
    }

    @Operation(summary = "获取所有可见模板摘要", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @GetMapping("/list")
    public Result<List<TemplateAbstractVO>> list() {
        return Result.success(templateService.listAll());
    }

    @Operation(summary = "获取私有模板摘要", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @GetMapping("/private")
    public Result<List<TemplateAbstractVO>> getPrivate() {
        return Result.success(templateService.getPrivate());
    }

    @Operation(summary = "根据id获取模板详情", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @GetMapping("/{id}")
    public Result<TemplateVO> getById(@PathVariable Long id) {
        return Result.success(templateService.id(id));
    }

    @Operation(summary = "创建私有模板", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PostMapping("/create")
    public Result<?> create(TemplateDTO templateDTO) {
        templateService.create(templateDTO);
        return Result.success();
    }

    @Operation(summary = "条件修改模板", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @PutMapping("/update")
    public Result<?> update(TemplateUpdateDTO templateUpdateDTO) {
        templateService.updateTemp(templateUpdateDTO);
        return Result.success();
    }

    @Operation(summary = "根据id删除模板", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        templateService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "批量删除模板", parameters = {@Parameter(
            name = TOKEN_NAME, required = true,
            in = ParameterIn.HEADER,
            description = WEB_HEADER_ADVICE,
            example = WEB_HEADER_VAR)})
    @DeleteMapping("/batch/{ids}")
    public Result<?> delete(@PathVariable List<Long> ids) {
        templateService.deleteBatchIds(ids);
        return Result.success();
    }
}
