package net.jinyiyun.common.lib;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import net.jinyiyun.common.config.AliOssConfig;
import net.jinyiyun.common.exception.AlertException;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * OSS操作 帮助类库
 *
 * @author dongshixiao
 * @date 2020/12/18 16:59
 */
public class AliOssLib {

    /**
     * 返回服务端加密签名
     *
     * @return Map<String, Object>
     */
    public static Map<String, Object> getPolicySignature() {
        AliOssConfig aliOssConfig = new AliOssConfig();
        // 用户上传文件时指定的前缀。   "files/2020/12/xx.jpg"
        final LocalDate now = LocalDate.now();
        String dir = "files/" + now.getYear() + "/" + new DecimalFormat("00").format(now.getMonthValue()) + "/";

        // 创建ossClient实例
        OSS ossClient = new OSSClientBuilder().build(aliOssConfig.getEndpoint(), aliOssConfig.getAccessKeyId(), aliOssConfig.getAccessKeySecret());
        try {

        } catch (Exception e) {

        }
        // 超时时间 默认 60s内不需要获取
        long expireEndTime = System.currentTimeMillis() + 60 * 1000;
        Date expiration = new Date(expireEndTime);
        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。  此处默认为20M
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1024 * 1024 * 20);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);


        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);


        // 返回的数据
        Map<String, Object> respMap = new LinkedHashMap<>();
        respMap.put("accessid", aliOssConfig.getAccessKeyId());
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", "https://" + aliOssConfig.getBucketName() + "." + aliOssConfig.getEndpoint());
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        respMap.put("oss_url", aliOssConfig.getOssUrl());

        return respMap;
    }


    /**
     * 获取sts授权
     *
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSts() {
        AliOssConfig aliOssConfig = new AliOssConfig();
        IClientProfile profile = DefaultProfile.getProfile("", aliOssConfig.getAccessKeyId(), aliOssConfig.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        // 创建一个 AssumeRoleRequest 并设置请求参数
        final AssumeRoleRequest request = new AssumeRoleRequest();
        // 固定为中国大陆
        request.setSysEndpoint("sts.aliyuncs.com");
        request.setSysMethod(MethodType.POST);
        request.setRoleArn(aliOssConfig.getRoleArn());
        request.setRoleSessionName("alioss-sts");
        // request.setDurationSeconds(aliOssConfig.getTokenExpireTime()); // 设置凭证有效时间
        try {
            final AssumeRoleResponse response = client.getAcsResponse(request);
            Map<String, Object> res = new HashMap<>(10);
            res.put("accessKeyId", response.getCredentials().getAccessKeyId());
            res.put("accessKeySecret", response.getCredentials().getAccessKeySecret());
            res.put("expiration", response.getCredentials().getExpiration());
            res.put("stsToken", response.getCredentials().getSecurityToken());
            res.put("bucket", aliOssConfig.getBucketName());
            res.put("endpoint", aliOssConfig.getEndpoint());
            res.put("region", aliOssConfig.getRegion());
            return res;
        } catch (ClientException e) {
            throw new AlertException("获取sts失败:" + e.getMessage());
        }
    }


}

