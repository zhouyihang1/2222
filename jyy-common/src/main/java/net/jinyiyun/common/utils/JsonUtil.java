package net.jinyiyun.common.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.util.List;

/**
 * @author dongshixiao
 */
public class JsonUtil {

    /**
     * 将对象转换为json字符串
     */
    public static String obj2string(Object obj) {
        StringWriter sw = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(sw, obj);
        } catch (Exception e) {
        }
        return sw.toString();
    }

    /**
     * 将字符串转list对象
     *
     * @param <T> 类型
     * @param jsonStr 字符串
     * @param cls 类
     * @return list
     */
    public static <T> List<T> str2list(String jsonStr, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> objList = null;
        try {
            JavaType t = mapper.getTypeFactory().constructParametricType(
                    List.class, cls);
            objList = mapper.readValue(jsonStr, t);
        } catch (Exception e) {
        }
        return objList;
    }

    /**
     * 将字符串转为对象
     *
     * @param <T> 类型
     * @param jsonStr 字符串
     * @param cls 类
     * @return 对象
     */
    public static <T> T str2obj(String jsonStr, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        try {
            obj = mapper.readValue(jsonStr, cls);
        } catch (Exception e) {
        }
        return obj;
    }

}
