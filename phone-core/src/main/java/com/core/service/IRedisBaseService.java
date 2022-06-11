package com.core.service;

import java.util.Map;

/**
 * Created by Owner on 2018/9/27.
 */
public interface IRedisBaseService<K, V> {

    /**
     * 新增
     *
     * @param key   键值
     * @param value
     */
    public void add(K key, V value);

    /**
     * 新增
     *
     * @param key    键值
     * @param value  值
     * @param minute 存储分钟
     */
    public void addTimeMinute(K key, V value, int minute);


    /**
     * 新增
     *
     * @param key    键值
     * @param value  值
     * @param seconds 存储秒钟
     */
    public void addTimeSeconds(K key, V value, int seconds);

    /**
     * 查询
     *
     * @param key 键
     * @return
     */
    public V get(K key);


    /**
     * 查询
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public void addToken(K key, V value);


    /**
     * 删除
     *
     * @param key 键
     */
    public void delete(K key);

    /**
     * Map添加
     * @param key
     * @param value
     */
    public void addMap(K key, Map<K, V> value);

    /**
     * Map查询
     *
     * @param key 键
     * @return
     */
    public Map<K, V> getMap(K key);

}
