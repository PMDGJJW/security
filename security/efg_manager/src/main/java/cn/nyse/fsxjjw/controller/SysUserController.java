package cn.nyse.fsxjjw.controller;

import cn.nyse.fsxjjw.common.entity.JsonResult;
import cn.nyse.fsxjjw.common.utils.ResultTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysUserController {

    @GetMapping("/getUser")
    public JsonResult test() {
        return ResultTool.success("hello world");
    }

    @GetMapping("/getRole")
    public JsonResult getRole() {
        return ResultTool.success("hello world ROLE");
    }

}
