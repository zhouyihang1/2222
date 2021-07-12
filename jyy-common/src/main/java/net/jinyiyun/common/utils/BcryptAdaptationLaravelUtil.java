package net.jinyiyun.common.utils;

import net.jinyiyun.common.exception.AlertException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.util.StringUtils;

/**
 * BCrypt加密 兼容laravel框架
 *
 * @author dongshixiao
 */
public class BcryptAdaptationLaravelUtil {
    /**
     * 根据密码生成hash密码 laravel可直接解密
     */
    public static String encode(String password) {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        return hash.replaceFirst("2a", "2y");
    }

    /**
     * 判断
     *
     * @param password 密码
     * @param hash     加密串
     */
    public static Boolean verify(String password, String hash) {
        if (StringUtils.isEmpty(hash)) {
            throw new AlertException("账户和密码不匹配");
        }
        return BCrypt.checkpw(password, hash.replaceFirst("2y", "2a"));
    }

}
