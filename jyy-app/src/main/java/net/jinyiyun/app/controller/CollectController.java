package net.jinyiyun.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jinyiyun.app.framework.auth.AppAuth;
import net.jinyiyun.common.annotation.NoLogin;
import net.jinyiyun.common.core.Result;
import net.jinyiyun.framework.entity.Collect;
import net.jinyiyun.framework.entity.Commodity;
import net.jinyiyun.framework.entity.User;
import net.jinyiyun.framework.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouyihang
 */
@RestController
@RequestMapping("/coll")
@Api(description = "收藏接口")
public class CollectController {
    @Autowired
    CollectService collectService;

    @NoLogin
    @GetMapping("/list")
    @ApiOperation(value = "查询我的收藏")
    public Object listColl(){
        User user = AppAuth.getLoginThrowException();
        List<Commodity> comm = collectService.selectAllByUserId(user.getId());
        return Result.success(comm);
    }

    @NoLogin
    @GetMapping("/dele")
    @ApiOperation(value = "根据商品id删除我的收藏")
    public Object deleColl(Integer id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("collect_commId",id);
        boolean result = collectService.remove(queryWrapper);
        return result ? Result.success("删除收藏成功") : Result.error("删除收藏失败");
    }

    @NoLogin
    @GetMapping("/add")
    @ApiOperation(value = "添加收藏")
    public Object addColl(Integer id){
        Collect collect = new Collect();
        collect.setCollectCommId(id);
        collect.setUserId(AppAuth.getLogin().getId());
        boolean saveResult = collectService.save(collect);
        return saveResult ? Result.success("添加收藏成功") : Result.error("添加收藏失败");
    }
}
