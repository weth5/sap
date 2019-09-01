package com.whx.redisUitl;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * Created by Administrator on 2019/8/13.
 */
public class PubSubDemo {
    @Autowired
    private static JedisCluster jedisCluster;
    public static void main(String[] args) {
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "127.0.0.1"));

        SubThread subThread = new SubThread(jedisCluster);  //订阅者
        subThread.start();

        Publisher publisher = new Publisher(jedisCluster);    //发布者
        publisher.start();
    }
}
