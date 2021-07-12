package net.jinyiyun.common.utils;

import java.util.Random;

/**
 * 随机四位数验证码
 * @author zhouyihang
 */
public class CodeUtil {

    public static String runNumber() {
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        String code = sb.toString();
        return code;
    }
}
