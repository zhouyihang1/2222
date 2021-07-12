package net.jinyiyun.framework.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.jinyiyun.framework.mapper.OperationLogMapper;
import net.jinyiyun.framework.entity.OperationLog;
import net.jinyiyun.framework.service.OperationLogService;
/**
 * @author zhouyihang
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService{

}
