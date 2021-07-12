package net.jinyiyun.framework.service;

import net.jinyiyun.framework.entity.Collect;
import com.baomidou.mybatisplus.extension.service.IService;
import net.jinyiyun.framework.entity.Commodity;

import java.util.List;

/**
 *
 * @author zhouyihang
 */
public interface CollectService extends IService<Collect> {

    /**
     * 查询我的收藏
     * @param id 用户id
     * @return 我的收藏
     */
    List<Commodity> selectAllByUserId(Integer id);
}
