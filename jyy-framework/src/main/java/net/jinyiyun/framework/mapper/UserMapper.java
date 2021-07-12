package net.jinyiyun.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.jinyiyun.framework.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author zhouyihang
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}