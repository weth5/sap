package com.whx.redisUitl;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * Created by Administrator on 2019/8/13.
 */
//接受者怎么接受发布者发送的消息？
public class SubThread extends Thread {
    @Autowired
    private JedisCluster jedisCluster;
    private final Subscriber subscriber = new Subscriber();
    private final String channel = "mychannel";

    public SubThread(JedisCluster jedisCluster) {
        super("SubThread");
        this.jedisCluster = jedisCluster;
    }

    @Override
    public void run() {
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        try {
            jedisCluster.subscribe(subscriber, channel);    //通过subscribe 的api去订阅，入参是订阅者和频道名
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        }
    }
}
