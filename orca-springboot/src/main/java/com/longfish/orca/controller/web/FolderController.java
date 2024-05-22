package com.longfish.orca.controller.web;

import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.dto.FolderDTO;
import com.longfish.orca.pojo.dto.FolderUpdateDTO;
import com.longfish.orca.service.IFolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folder")
@Tag(name = "文件夹")
public class FolderController {

    @Autowired
    private IFolderService folderService;

    @Operation(summary = "获取文件夹树形结构")
    @GetMapping("/tree")
    public Result<?> tree() {
        return Result.success();
    }

    @Operation(summary = "创建文件夹")
    @PostMapping("/create")
    public Result<?> create(@RequestBody FolderDTO folderDTO) {
        folderService.create(folderDTO);
        return Result.success();
    }

    @Operation(summary = "修改文件夹")
    @PutMapping("/update")
    public Result<?> update(@RequestBody FolderUpdateDTO folderUpdateDTO) {
        folderService.updateName(folderUpdateDTO);
        return Result.success();
    }
}
