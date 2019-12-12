package org.spiral.java.sql.model;

/**
 * 消息对象
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @copyight spiral
 * @since 1.0 Version
 */
public class SetRequest {
    private final String key;

    private final Object value;

    public SetRequest(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

}
