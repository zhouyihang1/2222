package net.jinyiyun.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.jinyiyun.framework.entity.Commodity;
import net.jinyiyun.framework.service.CommodityService;
import net.jinyiyun.framework.mapper.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author zhouyihang
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity>
implements CommodityService{

    @Autowired
    CommodityMapper commodityMapper;
    @Override
    public List<Commodity> selectByCondition(Integer low, Integer up, String professional, Integer info) {
        return commodityMapper.selectByCondition(low,up,professional,info);
    }

    @Override
    public int updateClickTime(Integer id) {
        return commodityMapper.updateClickTime(id);
    }

    @Override
    public List<Commodity> listByUserId(Integer id) {
        return commodityMapper.selectByUserId(id);
    }

}




