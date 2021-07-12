package net.jinyiyun.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import net.jinyiyun.common.annotation.NoLogin;
import net.jinyiyun.common.core.Result;
import net.jinyiyun.framework.entity.Professional;
import net.jinyiyun.framework.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouyihang
 */
@RestController
@Api(description = "职业接口")
@RequestMapping("/job")
public class JobController {

    @Autowired
    ProfessionalService professionalService;

    @GetMapping("/query")
    @ApiOperation(value = "查询所有职业")
    @NoLogin
    public Object queryJob(){
        List<Professional> professionalList = professionalService.list();
        return Result.success(professionalList);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加职业")
    @NoLogin
    public Object addJob(@RequestBody Professional professional){
        boolean result = professionalService.save(professional);
        return result ? Result.success("添加职业成功") : Result.error("添加职业失败");
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除职业")
    @NoLogin
    public Object deleteJob(Integer id){
        boolean resultRemove = professionalService.removeById(id);
        return resultRemove ? Result.success("删除职业成功") : Result.error("删除职业失败");
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新职业")
    @NoLogin
    public Object updateJob(@RequestBody Professional professional){
        boolean resultUpdate = professionalService.updateById(professional);
        return resultUpdate ? Result.success("更新职业成功") : Result.error("更新职业失败");

    }
}
