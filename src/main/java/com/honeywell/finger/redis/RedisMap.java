package com.honeywell.finger.redis;

/**
 * RedisMap操作接口
 *
 */
public interface RedisMap {
    /**
     * 存储
     *
     * @param key
     * @param value
     */
    void put(String key, Object value);

    /**
     * 取值
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除RedisMap中键值
     *
     * @param keys
     * @return
     */
    Long remove(Object... keys);

    /**
     * 删除RedisMap
     */
    public Long removeAll();


    /**
     * 检查心跳
     */
     boolean exist(String key);


    void putSetTime(String key,Object value);
}
