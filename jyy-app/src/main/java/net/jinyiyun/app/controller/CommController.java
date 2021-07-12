package net.jinyiyun.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jinyiyun.app.framework.auth.AppAuth;
import net.jinyiyun.common.annotation.NoLogin;
import net.jinyiyun.common.core.Result;
import net.jinyiyun.framework.entity.Commodity;
import net.jinyiyun.framework.entity.Img;
import net.jinyiyun.framework.entity.User;
import net.jinyiyun.framework.service.CommodityService;
import net.jinyiyun.framework.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhouyihang
 */
@RestController
@RequestMapping("/comm")
@Api(description = "商品接口")
public class CommController {
    @Autowired
    CommodityService commodityService;

    @Autowired
    ImgService imgService;


    @GetMapping("/query")
    @ApiOperation(value = "查询全部商品")
    @NoLogin
    public Object queryAllComm(){
        List<Commodity> commodityList = commodityService.list();
        return Result.success(commodityList);
    }


    @PostMapping("/issue")
    @ApiOperation(value = "发布商品")
    @NoLogin
    @Transactional(rollbackFor = Exception.class)
    public Object issCommodity(@RequestParam String accountTitle,
                               @RequestParam String localService,
                               @RequestParam String surefireLevel,
                               @RequestParam String fightingCapacity,
                               @RequestParam String professional,
                               @RequestParam String equipProperties,
                               @RequestParam BigDecimal price,
                               @RequestParam String wx,
                               @RequestParam(required = false) String note,
                               @RequestBody List<Img> imgs){
        User user = AppAuth.getLoginThrowException();
        Commodity commodity = new Commodity();
        commodity.setUserId(user.getId());
        commodity.setAccountTitle(accountTitle);
        commodity.setLocalService(localService);
        commodity.setSurefireLevel(surefireLevel);
        commodity.setFightingCapacity(fightingCapacity);
        commodity.setProfessional(professional);
        commodity.setEquipProperties(equipProperties);
        commodity.setPrice(price);
        commodity.setWx(wx);
        commodity.setNote(note);
        for (Img img : imgs){
            img.setCommTitle(commodity.getAccountTitle());
        }
        boolean saveImgResult = imgService.saveBatch(imgs);
        boolean saveCommResult = commodityService.save(commodity);
        return saveCommResult && saveImgResult ? Result.success("发布商品成功") : Result.error("发布商品失败");
    }

    @GetMapping("/filter")
    @ApiOperation(value = "筛选商品")
    @NoLogin
    public Object filterCommodity(Integer low,Integer up,String professional,Integer info){
        List<Commodity> commodities = commodityService.selectByCondition(low, up, professional,info);
        return Result.success(commodities);
    }

    @GetMapping("/sortByTime")
    @ApiOperation(value = "根据发布时间排序商品")
    @NoLogin
    public Object sortByTime(){
        QueryWrapper<Commodity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_at");
        List<Commodity> commList = commodityService.list(wrapper);
        return Result.success(commList);
    }

    @GetMapping("/seeCommDetails")
    @ApiOperation(value = "根据id查询商品详情")
    @NoLogin
    public Object seeDetailsById(Integer id){
        Commodity commodity = commodityService.getById(id);
        //更新查看次数
        commodityService.updateClickTime(id);
        return Result.success(commodity);
    }

    @GetMapping("/seeMyComm")
    @ApiOperation(value = "查看我发布的商品")
    @NoLogin
    public Object seeMyComm(){
        User user = AppAuth.getLoginThrowException();
        Integer id = user.getId();
        List<Commodity> commodities = commodityService.listByUserId(id);
        return Result.success(commodities);
    }

    @GetMapping("/deleteComm")
    @ApiOperation(value = "删除商品")
    @NoLogin
    public Object deleteComm(Integer id){
        boolean resultRemove = commodityService.removeById(id);
        return resultRemove ? Result.success("删除商品成功") : Result.error("删除商品失败");
    }

    @PostMapping("/updateComm")
    @ApiOperation(value = "更新商品")
    @NoLogin
    public Object updateComm(@RequestBody Commodity commodity){
        boolean resultUpdate = commodityService.updateById(commodity);
        return resultUpdate ? Result.success("更新商品成功") : Result.error("更新商品失败");
    }
}
