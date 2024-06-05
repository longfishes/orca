package com.longfish.orca.controller.web;


import com.longfish.orca.context.SearchStrategyContext;
import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.dto.DocumentByTempDTO;
import com.longfish.orca.pojo.dto.DocumentDTO;
import com.longfish.orca.pojo.dto.DocumentUpdateDTO;
import com.longfish.orca.pojo.dto.PageDTO;
import com.longfish.orca.pojo.vo.DocumentAbstractVO;
import com.longfish.orca.pojo.vo.DocumentSearchVO;
import com.longfish.orca.pojo.vo.DocumentVO;
import com.longfish.orca.pojo.vo.PageVO;
import com.longfish.orca.service.IDocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/document")
@Tag(name = "文档相关")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private SearchStrategyContext searchStrategyContext;

    @Operation(summary = "创建新文档")
    @PostMapping("/create")
    public Result<?> create(@RequestBody DocumentDTO documentDTO) {
        documentService.create(documentDTO);
        return Result.success();
    }

    @Operation(summary = "根据模板创建新文档")
    @PostMapping("/createByTemp")
    public Result<?> createByTemp(@RequestBody DocumentByTempDTO documentByTempDTO) {
        documentService.createByTemp(documentByTempDTO);
        return Result.success();
    }

    @Operation(summary = "搜索文档")
    @GetMapping("/search")
    public Result<List<DocumentSearchVO>> search(String keywords) {
        return Result.success(searchStrategyContext.executeSearchStrategy(keywords));
    }

    @Operation(summary = "获取最近文档摘要")
    @GetMapping("/recent")
    public Result<List<DocumentAbstractVO>> recent(Long limit) {
        return Result.success(documentService.recent(limit));
    }

    @Operation(summary = "分页条件查询")
    @PostMapping("/list")
    public Result<PageVO<DocumentAbstractVO>> list(@RequestBody PageDTO pageDTO) {
        return Result.success(documentService.listAll(pageDTO));
    }

    @Operation(summary = "获取置顶文档摘要")
    @GetMapping("/top")
    public Result<List<DocumentAbstractVO>> top() {
        return Result.success(documentService.top());
    }

    @Operation(summary = "获取回收站文档摘要")
    @GetMapping("/trash")
    public Result<List<DocumentAbstractVO>> trash() {
        return Result.success(documentService.trash());
    }

    @Operation(summary = "根据路径文档摘要")
    @GetMapping("/path")
    public Result<List<DocumentAbstractVO>> path(String path) {
        return Result.success(documentService.path(path));
    }

    @Operation(summary = "根据id获取文档详情")
    @GetMapping("/{id}")
    public Result<DocumentVO> getById(@PathVariable Long id) {
        return Result.success(documentService.id(id));
    }

    @Operation(summary = "根据id逻辑删除文档")
    @DeleteMapping("/{id}")
    public Result<?> deleteById(@PathVariable Long id) {
        documentService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "批量逻辑删除文档")
    @DeleteMapping("/batch/{ids}")
    public Result<?> deleteByIdBatch(@PathVariable List<Long> ids) {
        documentService.deleteByIdBatch(ids);
        return Result.success();
    }

    @Operation(summary = "根据id找回文档")
    @PutMapping("/restore/{id}")
    public Result<?> restore(@PathVariable Long id) {
        documentService.restore(id);
        return Result.success();
    }

    @Operation(summary = "批量找回文档")
    @PutMapping("/restore/batch/{ids}")
    public Result<?> restore(@PathVariable List<Long> ids) {
        documentService.restoreBatch(ids);
        return Result.success();
    }

    @Operation(summary = "根据id物理删除文档")
    @DeleteMapping("/delete/{id}")
    public Result<?> deleteByIdTruly(@PathVariable Long id) {
        documentService.deleteByIdTruly(id);
        return Result.success();
    }

    @Operation(summary = "批量物理删除文档")
    @DeleteMapping("/delete/batch/{ids}")
    public Result<?> deleteByIdBatchTruly(@PathVariable List<Long> ids) {
        documentService.deleteByIdBatchTruly(ids);
        return Result.success();
    }

    @Operation(summary = "条件修改文档")
    @PutMapping("/update")
    public Result<?> lambdaUpdate(@RequestBody DocumentUpdateDTO documentUpdateDTO) {
        documentService.updateDoc(documentUpdateDTO);
        return Result.success();
    }
}
