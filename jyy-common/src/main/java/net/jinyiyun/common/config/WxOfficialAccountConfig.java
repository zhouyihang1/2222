package net.jinyiyun.common.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 微信公众号
 *
 * @author dongshixiao
 * @date 2021/2/3 9:34
 */
@Data
@Component
public class WxOfficialAccountConfig {

    String appId = "";

    String appSecret = "";


    String md5Key = "";

    String merchantId = "";

    String ip = "";

}
