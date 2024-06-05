package com.longfish.orca.controller.web;

import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.dto.FolderDTO;
import com.longfish.orca.pojo.dto.FolderUpdateDTO;
import com.longfish.orca.pojo.entity.Folder;
import com.longfish.orca.service.IFolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folder")
@Tag(name = "文件夹")
public class FolderController {

    @Autowired
    private IFolderService folderService;

    @Operation(summary = "获取根路径文件夹")
    @GetMapping("/")
    public Result<List<Folder>> root() {
        return Result.success(folderService.root());
    }



    @Operation(summary = "创建文件夹")
    @PostMapping("/create")
    public Result<?> create(@RequestBody FolderDTO folderDTO) {
        folderService.create(folderDTO);
        return Result.success();
    }

    @Operation(summary = "修改文件夹名称")
    @PutMapping("/update")
    public Result<?> update(@RequestBody FolderUpdateDTO folderUpdateDTO) {
        folderService.updateName(folderUpdateDTO);
        return Result.success();
    }

    @Operation(summary = "根据id删除文件夹")
    @DeleteMapping("/{id}")
    public Result<?> deleteById(@PathVariable Long id) {
        folderService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "批量删除文件夹")
    @DeleteMapping("/{ids}")
    public Result<?> deleteBatchIds(@PathVariable List<Long> ids) {
        folderService.deleteBatchIds(ids);
        return Result.success();
    }
}
