package net.jinyiyun.framework.service;

import net.jinyiyun.framework.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author zhouyihang
 */
public interface CommodityService extends IService<Commodity> {

    /**
     * 筛选商品
     * @param low 最低
     * @param up 最高
     * @param professional 职业
     * @param info 提示
     * @return
     */
    List<Commodity> selectByCondition(Integer low, Integer up, String professional, Integer info);

    /**
     * 更新点击次数
     * @param id
     * @return
     */
    int updateClickTime(Integer id);


    /**
     * 获取用户发布的商品
     * @param id 用户id
     * @return
     */
    List<Commodity> listByUserId(Integer id);


}
