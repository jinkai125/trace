package com.trace.keywords.metadata;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.trace.keywords.Keywords;

/**
 * 存储关键字元数据
 * 
 * @author jinkai125@126.com
 *
 */
public final class Metadata {

    /**
     * 数据存储仓库
     */
    private final Map<String, Keywords> METADATA = Collections.synchronizedMap(new HashMap<>());

    private final Map<Class<?>, Set<String>> FIELDS = Collections.synchronizedMap(new HashMap<>());

    /**
     * 使用单例模式
     */
    private static final Metadata INSTANCE = new Metadata();

    private Metadata() {
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static Metadata getInstance() {
        return INSTANCE;
    }

    /**
     * 添加关键字,该方法如果存在相关关键字则抛出异常
     * 
     * @param keywords
     */
    public void add(Keywords keywords) {
        String key = genKey(keywords.getField());
        if (METADATA.containsKey(key)) {
            throw new IllegalArgumentException(key + " has more keywords");
        }
        Class<?> type = keywords.getField().getDeclaringClass();
        Set<String> set = FIELDS.get(type);
        if (set == null) {
            FIELDS.put(type, Collections.synchronizedSet(new HashSet<>()));
        }
        FIELDS.get(type).add(key);
        METADATA.put(key, keywords);
    }

    /**
     * 删除关键字
     * 
     * @param keywords
     */
    public void remove(Keywords keywords) {
        String key = genKey(keywords.getField());
        Class<?> type = keywords.getField().getDeclaringClass();
        Set<String> set = FIELDS.get(type);
        if (set != null) {
            FIELDS.get(type).remove(key);
        }
        METADATA.remove(key);
    }

    /**
     * 替换关键字,该方法在没有关键字时添加,存在时进行替换
     * 
     * @param keywords
     */
    public void replace(Keywords keywords) {
        String key = genKey(keywords.getField());
        Class<?> type = keywords.getField().getDeclaringClass();
        Set<String> set = FIELDS.get(type);
        if (set == null) {
            FIELDS.put(type, Collections.synchronizedSet(new HashSet<>()));
        }
        FIELDS.get(type).add(key);
        METADATA.put(key, keywords);
    }

    /**
     * 清空所有
     */
    public void clear() {
        FIELDS.clear();
        METADATA.clear();
    }

    /**
     * 删除指定类型的key列表
     * 
     * @param type
     */
    public void clear(Class<?> type) {
        Set<String> set = FIELDS.get(type);
        if (set == null || set.isEmpty()) {
            return;
        }
        for (String key : set) {
            METADATA.remove(key);
        }
    }

    /**
     * 获取指定字段的关键字
     * 
     * @param field
     * @return
     */
    public Keywords getKeywords(Field field) {
        return METADATA.get(genKey(field));
    }

    /**
     * 获取当前类型下的所有关键字,包含父类
     * 
     * @param type
     * @return
     */
    public Map<String, Keywords> getKeywords(Class<?> type) {

        Map<String, Keywords> keywords = new HashMap<>();
        if (type.isInterface() || type.isAnnotation() || type.isEnum() || type.isPrimitive()
                || type == java.sql.Date.class || type == Date.class || type == LocalDateTime.class
                || type == LocalDate.class || type == LocalTime.class
                || Number.class.isAssignableFrom(type) || Boolean.class == type
                || String.class == type || type == Object.class) {
            return keywords;
        }
        if (type.getName().startsWith("org.spring") || type.getName().startsWith("org.apache")) {
            return keywords;
        }

        Set<String> set = FIELDS.get(type);
        if (set != null) {
            for (String key : set) {
                keywords.put(key, METADATA.get(key));
            }
        }
        keywords.putAll(getKeywords(type.getSuperclass()));
        return keywords;
    }

    private static String genKey(Field field) {
        return field.getDeclaringClass().getName() + "." + field.getName();
    }
}
