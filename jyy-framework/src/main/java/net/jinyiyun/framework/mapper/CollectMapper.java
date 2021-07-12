package net.jinyiyun.framework.mapper;

import net.jinyiyun.framework.entity.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.jinyiyun.framework.entity.Commodity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhouyihang
 * @Entity net.jinyiyun.framework.entity.Collect
 */
@Repository
public interface CollectMapper extends BaseMapper<Collect> {

    /**
     * 查询我的收藏
     * @param id 用户id
     * @return 我的收藏
     */
    List<Commodity> selectAllByUserId(Integer id);
}




