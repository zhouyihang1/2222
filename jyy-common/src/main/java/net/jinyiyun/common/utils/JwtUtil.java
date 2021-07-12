package net.jinyiyun.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


/**
 * JWT操作类
 *
 * @author dongshixiao
 */
public class JwtUtil {
    // 过期时间 24 小时
    // private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;

    /**
     * 密钥
     */
    private static final String SECRET = "2B53Bbz0UI1N2MA7PkYgiXj2q3t1n3h4m5aZxp";



    /**
     * 生成 token, 5min后过期
     *
     * @param id 用户ID
     * @return 加密的token
     */
    public static String createToken(Integer id) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带id信息
        return JWT.create()
                .withClaim("id", id)
                // 签发的时间
                .withIssuedAt(new Date())
                //到期时间
                // .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }


    /**
     * 校验 token 是否正确
     *
     * @param token 密钥
     * @param id    用户ID
     * @return 是否正确
     */
    public static boolean verify(String token, Integer id) {
        try {
            token = token.replaceFirst("Bearer ", "");
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了存入的信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("id", id)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static Integer getId(String token) {
        try {
            token = token.replaceFirst("Bearer ", "");
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


}
