package com.meizhou.mybatis.mapper;

import com.meizhou.mybatis.cache.AbstractCacheExecutorConfig;
import com.meizhou.mybatis.cache.CacheTableConfig;
import com.meizhou.mybatis.cache.ICacheClient;
import com.meizhou.mybatis.cache.RedisCacheClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by meizhou on 2019/1/3.
 */
public class FlyCacheExecutorConfig extends AbstractCacheExecutorConfig {

    private Map<String, CacheTableConfig> cacheTableConfigMap = new ConcurrentHashMap<>();

    public FlyCacheExecutorConfig() {
        ICacheClient cacheClient = new RedisCacheClient("localhost", 6379, "123456");
        List<String> stringList = new ArrayList<>();
        stringList.add("id");
        CacheTableConfig cacheTableConfig = CacheTableConfig.build("v20", "user_info", stringList, stringList, cacheClient);
        cacheTableConfigMap.put("user_info", cacheTableConfig);
    }

    @Override
    public Map<String, CacheTableConfig> getCacheTableConfigMap() {
        return cacheTableConfigMap;
    }
}
