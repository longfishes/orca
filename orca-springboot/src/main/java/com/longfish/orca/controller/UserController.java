package com.longfish.orca.controller;


import com.longfish.orca.annotation.AccessLimit;
import com.longfish.orca.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @AccessLimit(seconds = 10, maxCount = 1)
    @GetMapping("/t")
    public Result<?> t() {
        return Result.success();
    }

}
