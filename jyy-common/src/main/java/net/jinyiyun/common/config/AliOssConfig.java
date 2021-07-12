package net.jinyiyun.common.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS 配置信息
 *
 * @author dongshixiao
 * 放弃使用注入配置方式  减少app和amdin统一配置
 * //@ConfigurationProperties(prefix = "alioss")
 */
@Data
@Component
public class AliOssConfig {
    String accessKeyId = "";
    String accessKeySecret = "";
    String roleArn = "";
    String bucketName = "";
    String region = "oss-cn-shanghai";
    String endpoint = "oss-cn-shanghai.aliyuncs.com";
    Boolean cname = true;
    String ossUrl = "";
    String ossSig = "H5QWfjsahf2344015flzpLHF9UIA";
}
