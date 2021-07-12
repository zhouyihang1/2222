package net.jinyiyun.common.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 微信小程序
 *
 * @author dongshixiao
 * @date 2021/2/3 9:34
 */
@Data
@Component
public class WxAppletsConfig {

    String appId = "";

    String appSecret = "";


}
