package com.trace.keywords;

import java.lang.reflect.Field;
import com.trace.keywords.masker.NoneMasker;
import com.trace.keywords.metadata.Metadata;
import com.trace.keywords.utils.FieldUtils;

/**
 * 关键字接口
 * 
 * @author jinkai125@126.com
 *
 */
public interface Keywords {

    /**
     * 获取关键字别名
     * 
     * @return
     */
    default String getAlias() {
        return "";
    }

    /**
     * 获取脱敏器
     * 
     * @return
     */
    default Masker getMasker() {
        return new NoneMasker();
    }

    /**
     * 获取对应字段
     * 
     * @return
     */
    Field getField();

    /**
     * 保存
     */
    default void save() {
        Metadata.getInstance().replace(this);
    }

    /**
     * 删除
     */
    default void delete() {
        Metadata.getInstance().remove(this);
    }

    /**
     * 解析参数,对当前关键字进行解析并脱敏
     * 
     * @param obj
     * @return
     * @throws Exception
     */
    default String resolve(Object obj) throws Exception {
        Field field = getField();
        return getMasker().masking(field.getName(), FieldUtils.get(field, obj));
    }
}
