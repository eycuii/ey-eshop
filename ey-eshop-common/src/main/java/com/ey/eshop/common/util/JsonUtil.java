package com.ey.eshop.common.util;

import com.ey.eshop.common.exception.SystemException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class JsonUtil {

    private JsonUtil() {
    }

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // 设置时区
        mapper.setTimeZone(TimeZone.getDefault());

        // 设置时间为 yyyy-MM-dd HH:mm:ss 日期
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

        // 序列化BigDecimal时之间输出原始数字还是科学计数，默认false，即是否以toPlainString()科学计数方式来输出
        mapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, false);

        // 设定是否使用Enum的toString函数来读取Enum, 为False时使用Enum的name()函数来读取Enum,
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        // 如果输入不存在的字段时不会报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 使用默认的Jackson注解
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
    }

    public static <T> T jsonToObject(String json, Class<T> objectType) {
        try {
            return mapper.readValue(json, objectType);
        } catch (Exception e) {
            log.error(e.getMessage() + ": " + json, e);
            throw new SystemException();
        }
    }

    public static <T> T jsonToObject(String json, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            log.error(e.getMessage() + ": " + json, e);
            throw new SystemException();
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> objectType) {
        try {
            CollectionLikeType t = mapper.getTypeFactory().constructCollectionLikeType(List.class, objectType);
            return mapper.readValue(json, t);
        } catch (Exception e) {
            log.error(e.getMessage() + ": " + json, e);
            throw new SystemException();
        }
    }

    public static <K, V> Map<K, V> jsonToMap(String json, Class<K> keyType, Class<V> valueType) {
        try {
            MapLikeType t = mapper.getTypeFactory().constructMapLikeType(Map.class, keyType, valueType);
            return mapper.readValue(json, t);
        } catch (Exception e) {
            log.error(e.getMessage() + ": " + json, e);
            throw new SystemException();
        }
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException();
        }
    }
}
