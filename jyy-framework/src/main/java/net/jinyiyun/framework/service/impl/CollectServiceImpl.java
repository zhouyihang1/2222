package net.jinyiyun.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.jinyiyun.framework.entity.Collect;
import net.jinyiyun.framework.entity.Commodity;
import net.jinyiyun.framework.service.CollectService;
import net.jinyiyun.framework.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author zhouyihang
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
implements CollectService{

    @Autowired
    CollectMapper collectMapper;
    @Override
    public List<Commodity> selectAllByUserId(Integer id) {
        return collectMapper.selectAllByUserId(id);
    }
}




