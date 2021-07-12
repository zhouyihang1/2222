package net.jinyiyun.app.framework.auth;

import net.jinyiyun.common.enums.ResultEnum;
import net.jinyiyun.common.exception.AlertException;
import net.jinyiyun.common.utils.JwtUtil;
import net.jinyiyun.common.utils.RequestUtil;
import net.jinyiyun.common.utils.SpringContextUtil;
import net.jinyiyun.framework.constant.Constant;
import net.jinyiyun.framework.entity.User;
import net.jinyiyun.framework.service.UserService;

/**
 * 获取登录用户
 *
 * @author dongshixiao
 * @date 2021/3/10 9:40
 */
public class AppAuth {

    /**
     * 线程安全的登录用户
     */
    public static ThreadLocal<User> login = new ThreadLocal<>();

    /**
     * 存储登录用户信息
     *
     * @param user User
     */
    public static void setLogin(User user) {
        login.set(user);
    }


    /**
     * 判断是否登录
     *
     * @return boolean 是否登录
     */
    public static boolean isLogin() {
        return null != getLogin();
    }

    /**
     * 获取登录用户
     * @return Admin
     */
    public static User getLogin() {
        User user = login.get();
        // 尝试从request中拿
        if (null == user) {
            user = getLoginByToken();
        }
        return user;
    }

    /**
     * 获取登录用户 用户不存在则报错
     *
     * @return Admin
     */
    public static User getLoginThrowException() {
        User user = getLogin();
        if (null == user) {
            throw new AlertException(ResultEnum.HTTP_UNAUTHORIZED);
        }
        return user;
    }

    public static void remove() {
        login.remove();
    }


    /**
     * 从请求头中获取token存全局变量中
     */
    public static void setLoginByToken() {
        // 从 http 请求头中取出 token
        String token = RequestUtil.getRequest().getHeader(Constant.JWT_TOKEN_HEAD_NAME);
        if (null == token) {
            throw new AlertException("请求中无token,请重新登录", 401);
        }
        Integer id = JwtUtil.getId(token);
        if (id == null || !JwtUtil.verify(token, id)) {
            throw new AlertException("token验证失败,请重新登录", 401);
        }
        User user = SpringContextUtil.getBean(UserService.class).getById(id);
        if (null == user) {
            throw new AlertException("用户不存在,请重新登录", 401);
        }
        // 保存用户信息 让每个方法都可以访问
        AppAuth.setLogin(user);
    }

    /**
     * 根据token获取user
     *
     * @return User
     */
    public static User getLoginByToken() {
        String token = RequestUtil.getRequest().getHeader(Constant.JWT_TOKEN_HEAD_NAME);
        if (null == token) {
            return null;
        }
        Integer id = JwtUtil.getId(token);
        if (id == null || !JwtUtil.verify(token, id)) {
            return null;
        }
        User user = SpringContextUtil.getBean(UserService.class).getById(id);
        if (null == user) {
            return null;
        }
        AppAuth.setLogin(user);
        return user;
    }

}
