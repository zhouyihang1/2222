package net.jinyiyun.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS 配置信息
 *
 * @author dongshixiao
 */
@Data
@Component
public class AlipayConfig {
    /**
     * 支付宝gatewayUrl
     */
    private String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    /**
     * 商户应用id
     */
    private String appid = "2016071801632055";
    /**
     * RSA私钥，用于对商户请求报文加签  需要格式: PKCS5(java适用)  如提示  algid parse error 错误 请使用 支付宝开放平台开发助手 -> 格式转换
     */
    private String appPrivateKey = "+8628VAmBO+KOcFCS1H2aTerZoXZTt+Wrati4jSyHgkPs/zQx/vJYEiHkxriYAtOC92FIYyj3Cu0e3/bO8vbbbwR6syrPsXS5ixTz5DQNoY0+ix9jkm+MHSNH+cPw3qHij4XsNR8qLwtr9/iGCdAxsO69Gl3off3t6g7Ve9Zl+UToFhjm2YRMZ6lMM8ynAgMBAAECggEBAMEdlV7iOZQt2XqA3qEnbvrTS/LkFjs3nNRi1c70KPBhKxzdlkEGTPq0F2dn+FkV899RKwD1ZFxnihAhpP0HpLOrRyIgUqTPD3a13p2umcQhXnB/EwPU2fKFShPeqCCj5UAlRXVvZJ31w0lXrn9H56qQSA5dNeC1s0GJcZCFBYlBtDCaoseXzzyUV2kzUEDUp9floOxrJm+PesY9vRMBluDckTKAD1dzTbEl/6R240MmWhEEmPxddUhPTkH+19LETkR3BOZCsJgXAw4sWzYnWxAbAW37c3+uUDJMAcTe+piQcK3NDl2plo95OcEL2r6AsZnKSPPdnlsz6+Ngtzx1RckCgYEA/DB50ayJKUUXJAh0SI2yGClgE6fReApQ0uK4p8FL7vTLCRVLUBGbeBg0nNHpLxJNrEK/CmJ8M5EQCplcG/B59D/K1SpcOGfCR2PzRActKDMC6xzZno9RKC8Ps4W4jEIHgB5o+aKJTZJyO8ft4jSpt5qvS3Ce0FxngYDYtiBhGNsCgYEAyEZylHC1CGU7ZXsf/zNI1ludEC5FjVdVZGIPdMez4RK9ki/kFNr/x52eRNyGDUA7nNDHNfipkog0czD/+5rYkXmOzRCa7WfTxJNzvx4ue89kpiWlzqv7rI9M9wJsVBiDn236nu31bN5STym51WflQ2gsE72rEOAHbDZoP+FBLyUCgYEAnya8ii0XdnsKqovHTs5VWVgrMNxUnivsa9n27K9ZC1ljFL1ihQpHkhe8hEcYoMMtT4vj4lraxPREKlw0O9GcNR9ZEwOjpVkdy03xhLn+roLa0aOoVp9deT2R7Bp0p9c3wovudbQ0cEb+XmnC32GaxasR+0r8fsQB8kFtAisW+8cCgYEAgHVPQQ8Zzwy/kFPtLg+DBJs2nw5O5l3CE+Uh7NG6+v6+QWoebZIaxbq/rYOVKCKvGeKAJIoemjJrEge36XZkusqxYa7o8KXMGyZI86k0P43DDR7Qv0No5kH0J+uR+E7Q/g3Y1t/ir7XaLZSh8dP2JcpHm1Hoy6YmnK45J+Afo1ECgYAkb4J76BJxJ4k4uvce3KjM6vcm52fgHnK8b/l4MOV1V77/nz52hRlhr2aVg42PRP20apIxbUoTeoja5S/zO4dAfJ42mMjnLJBQjnY46N2a+9vyWG1qTBn9QOTispkq2ykEzQSr4I3nZJYTQgmeLJ7rM2Lsed0V03v+H6GWDd9/8A==";

    /**
     * 支付宝RSA公钥，用于验签支付宝应答
     */
    private String alipayPublicKey = "MIIBIjANBgkqhkiG9ETHV4ZsDTx5GLiH0crCPAP3qzdwybJKtGxd5jGB15xRM059S7JFHe4NzFAfR57IFMcFhKxpy6+NODRP2l1kiliEWhwIDAQAB";
    /**
     * 签名类型
     */
    private String signType = "RSA2";
    /**
     * 格式
     */
    private String formate = "json";
    /**
     * 编码
     */
    private String charset = "UTF-8";
    /**
     * 同步地址
     */
    private String returnUrl;
    /**
     * 异步地址
     */
    private String notifyUrl = "https://phoenix.jinyiyun.net/pay/notify";
    /**
     * 最大查询次数
     */
    private static int maxQueryRetry = 5;
    /**
     * 查询间隔（毫秒）
     */
    private static long queryDuration = 5000;
    /**
     * 最大撤销次数
     */
    private static int maxCancelRetry = 3;
    /**
     * 撤销间隔（毫秒）
     */
    private static long cancelDuration = 3000;

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(this.getGatewayUrl(),
                this.getAppid(),
                this.getAppPrivateKey(),
                this.getFormate(),
                this.getCharset(),
                this.getAlipayPublicKey(),
                this.getSignType());
    }

}
