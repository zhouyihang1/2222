package net.jinyiyun.common.utils;


import org.springframework.util.StringUtils;

import java.util.*;


/**
 * 常用String操作类库
 *
 * @author dongshixiao
 */
public class StringUtil {

    /**
     * 字符串根据 逗号切割成set<Long>
     *
     * @param str 字符串
     * @return Set<Long>
     */
    public static Set<Long> toSetLong(String str) {
        Set<Long> set = new HashSet<>();
        if (StringUtils.isEmpty(str)) {
            return set;
        }
        String[] stringList = str.split(",");
        for (String s : stringList) {
            set.add(Long.valueOf(s));
        }
        return set;
    }

    /**
     * 字符串根据 逗号切割成set<String>
     *
     * @param str 字符串
     * @return Set<Long>
     */
    public static Set<String> toSetString(String str) {
        Set<String> set = new HashSet<>();
        if (StringUtils.isEmpty(str)) {
            return set;
        }
        String[] stringList = str.split(",");
        Collections.addAll(set, stringList);
        return set;
    }


}
