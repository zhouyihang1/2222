package net.jinyiyun.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jinyiyun.common.annotation.NoLogin;
import net.jinyiyun.common.core.Result;
import net.jinyiyun.framework.entity.Live;
import net.jinyiyun.framework.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouyihang
 */
@RestController
@RequestMapping("/live")
@Api(description = "直播接口")
public class LiveController {

    @Autowired
    LiveService liveService;

    @GetMapping("/queryAll")
    @ApiOperation(value = "查询全部直播")
    @NoLogin
    public Object selectAllLive(){
       return Result.success(liveService.list());
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加直播")
    @NoLogin
    public Object addLive(@RequestBody Live live){
        boolean saveResult = liveService.save(live);
        return saveResult ? Result.success("添加直播成功") : Result.error("添加直播失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新直播")
    @NoLogin
    public Object updateLive(Live live){
        boolean updateResult = liveService.updateById(live);
        return updateResult ? Result.success("更新直播成功") : Result.error("更新直播失败");
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除直播")
    @NoLogin
    public Object deleteLive(Integer id){
        boolean deleteResult = liveService.removeById(id);
        return deleteResult ? Result.success("删除直播成功") : Result.error("删除直播失败");
    }
}
