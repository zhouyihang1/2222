package net.jinyiyun.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jinyiyun.app.framework.aspectj.RemoveAuthAspect;
import net.jinyiyun.app.framework.auth.AppAuth;
import net.jinyiyun.common.annotation.NoLogin;
import net.jinyiyun.common.core.Result;
import net.jinyiyun.common.utils.CodeUtil;
import net.jinyiyun.common.utils.JwtUtil;
import net.jinyiyun.framework.entity.User;
import net.jinyiyun.framework.service.UserService;
import net.jinyiyun.framework.vo.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouyihang
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户接口")
public class UserController {
    @Autowired
    UserService userService;


    /**
     * 保存验证码
     */
    Code codeVo = new Code();

    @GetMapping("/getCode")
    @ApiOperation(value = "获取验证码")
    @NoLogin
    public String getCode(){
        String resultCode = CodeUtil.runNumber();
        codeVo.setCode(resultCode);
        return resultCode;
    }

    @PostMapping("/loginWithCode")
    @ApiOperation(value = "手机号验证码登录")
    @NoLogin
    public Object userLogin(@RequestParam String tel,
                            @RequestParam String code){
        if(!code.equals(codeVo.getCode())){
            return Result.error("验证码错误，请重新输入！");
        }else{
            User user = new User();
            user.setUsername(tel);
            user.setTel(tel);
            user.setLastLoginTime(LocalDateTime.now());
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("tel",tel);
            boolean saveUser = userService.saveOrUpdate(user, wrapper);
            if(saveUser){
                User resultUser = userService.getOne(wrapper);
                String token = JwtUtil.createToken(resultUser.getId());
                Map<String,String> map = new HashMap<>(2);
                map.put("msg","登录成功！");
                map.put("token",token);
                return Result.success(map);
            }else{
                return Result.error("登录失败!");
            }
        }
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/loginOut")
    @NoLogin
    public Object loginOut(){
        AppAuth.remove();
        return Result.success("退出登录成功！");
    }


    /*@PostMapping("/register")
    @ApiOperation(value = "注册")
    @NoLogin
    public Object userRegister(@RequestParam(required = true)String username,
                               @RequestParam(required = true)String password,
                               @RequestParam(required = true)String tel){
        User user = new User();
        String encode = BcryptAdaptationLaravelUtil.encode(password);
        user.setUsername(username);
        user.setPassword(encode);
        user.setTel(tel);
        boolean saveResult = userService.save(user);
        return saveResult ? Result.success("注册成功") : Result.error("注册失败");
    }*/

    /*@PostMapping("/login")
    @ApiOperation(value = "账号密码登录")
    @NoLogin
    public Object userLogin(@RequestParam(required = true)String username,
                            @RequestParam(required = true)String password){
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        User resultUser = userService.getOne(wrapper);
        String encode = BcryptAdaptationLaravelUtil.encode(password);
        Boolean verify = BcryptAdaptationLaravelUtil.verify(password, encode);
        if(resultUser != null && verify){
            AppAuth.setLogin(resultUser);
            Integer userId = resultUser.getId();
                String token = JwtUtil.createToken(userId);
                Map<String,String> map = new HashMap<>(2);
                map.put("msg","登录成功！");
                map.put("token",token);
                return Result.success(map);
        }else{
            return Result.error("登录失败！");
        }
    }*/
}
