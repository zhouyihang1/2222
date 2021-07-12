package net.jinyiyun.framework.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.jinyiyun.framework.entity.User;
import net.jinyiyun.framework.mapper.UserMapper;
import net.jinyiyun.framework.service.UserService;
/**
 * @author zhouyihang
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
