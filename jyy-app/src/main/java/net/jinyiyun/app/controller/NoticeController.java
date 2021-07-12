package net.jinyiyun.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jinyiyun.app.framework.auth.AppAuth;
import net.jinyiyun.common.core.Result;
import net.jinyiyun.framework.entity.Notice;
import net.jinyiyun.framework.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouyihang
 */
@Api(description = "公告接口")
@RestController
@RequestMapping("/not")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @PostMapping("/add")
    @ApiOperation("添加公告")
    public Object addNot(Notice notice){
        notice.setCreateBy(AppAuth.getLogin().getUsername());
        boolean save = noticeService.save(notice);
        return save ? Result.success("添加公告成功") : Result.error("添加公告失败");
    }

    @PostMapping("/update")
    @ApiOperation("更新公告")
    public Object updateNot(Notice notice){
        boolean update = noticeService.updateById(notice);
        return update ? Result.success("更新公告成功") : Result.error("更新公告失败");
    }

    @GetMapping("/delete")
    @ApiOperation("删除公告")
    public Object deleteNot(Integer id){
        boolean delete = noticeService.removeById(id);
        return delete ? Result.success("删除公告成功") : Result.error("删除公告失败");
    }

    @GetMapping("/query")
    @ApiOperation("查询公告")
    public Object queryNot(){
        List<Notice> notices = noticeService.list();
        return notices != null ? Result.success(notices) : Result.error("查询失败！");
    }
}
