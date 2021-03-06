package com.ternence.spring.redis.cluster.op;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ternence
 * @version 1.0
 * @since 2018/8/21 20:14
 */
@Component
public class RedisOperations {
    private final static Logger LOGGER = LoggerFactory.getLogger(RedisOperations.class);
    @Resource
    private JedisCluster jedisCluster;

    public List<String> scan() {

        Map<String, JedisPool> jedisPoolMap = jedisCluster.getClusterNodes();
        List<String> scanFinalResult = new ArrayList<>();
        jedisPoolMap.keySet().forEach(key -> {
            JedisPool jedisPool = jedisPoolMap.get(key);
            LOGGER.info("扫描节点{}", key);
            Jedis jedis = jedisPool.getResource();
            String cursor = "0";
            do {
                ScanResult<String> scanResult = jedis.scan(cursor);
                cursor = scanResult.getStringCursor();
                scanResult.getResult().forEach(item -> {
                    if (!item.startsWith("PV")) {
                        LOGGER.info("key : {} 不是以PV开头的数据", item);
                        scanFinalResult.add(item);
                    }
                });
                LOGGER.info("游标:{}", cursor);
            } while (!"0".equals(cursor));
        });

        LOGGER.info("扫描结果为:size={},result={}", scanFinalResult.size(), scanFinalResult);

        return scanFinalResult;
    }

}
















