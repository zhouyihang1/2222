package net.jinyiyun.framework.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import net.jinyiyun.framework.entity.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhouyihang
 * @Entity net.jinyiyun.framework.entity.Commodity
 */
@Repository
public interface CommodityMapper extends BaseMapper<Commodity> {
    /**
     * 筛选商品
     * @param low 最低
     * @param up 最高
     * @param professional 职业
     * @param info 分类
     * @return
     */
    List<Commodity> selectByCondition(Integer low, Integer up, String professional, Integer info);

    /**
     * 更新查看次数
     * @param id
     * @return
     */
    int updateClickTime(Integer id);

    /**
     * 查询全部商品
     * @param queryWrapper
     * @return
     */
    @Override
    List<Commodity> selectList(Wrapper<Commodity> queryWrapper);

    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return
     */
    @Override
    Commodity selectById(Serializable id);


    /**
     * 查询我的收藏
     * @param id 用户id
     * @return
     */
    List<Commodity> selectByUserId(Integer id);
}




