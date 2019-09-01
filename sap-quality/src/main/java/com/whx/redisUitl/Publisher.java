package com.whx.redisUitl;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2019/8/13.
 */
public class Publisher extends Thread {
    @Autowired
    JedisCluster jedisCluster;

    public Publisher(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public void run() {
        //它不这么做测试不了
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //连接池中取出一个连接
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedisCluster.publish("mychannel", line);   //从 mychannel 的频道上推送消息
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
