package net.jinyiyun.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jinyiyun.app.framework.auth.AppAuth;
import net.jinyiyun.common.annotation.NoLogin;
import net.jinyiyun.common.core.Result;
import net.jinyiyun.framework.entity.Feedback;
import net.jinyiyun.framework.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouyihang
 */
@RestController
@RequestMapping("/feed")
@Api(description = "反馈接口")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @NoLogin
    @PostMapping("/add")
    @ApiOperation(value = "添加反馈")
    public Object addFeed(String type,String content){
        Feedback feedback = new Feedback();
        feedback.setFeedbackType(type);
        feedback.setFeedbackContent(content);
        boolean save = feedbackService.save(feedback);
        return save ? Result.success("添加反馈成功") : Result.error("添加反馈失败");
    }

    @NoLogin
    @GetMapping("queryByUser")
    @ApiOperation("查看自己的反馈")
    public Object queryFeedByUserId(){
        Integer id = AppAuth.getLogin().getId();
        Map<String,Object> map = new HashMap<>(1);
        map.put("user_id",id);
        List<Feedback> feedbacks = feedbackService.listByMap(map);
        return Result.success(feedbacks);
    }

    @NoLogin
    @GetMapping("queryAll")
    @ApiOperation("查看所有反馈")
    public Object queryFeedAll(){
        List<Feedback> feedbacks = feedbackService.list();
        return Result.success(feedbacks);
    }
}
