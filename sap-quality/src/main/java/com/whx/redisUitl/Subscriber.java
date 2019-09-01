package com.whx.redisUitl;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by Administrator on 2019/8/13.
 */
//要发送的信息?
public class Subscriber extends JedisPubSub {
    //收到消息会调用
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, 通知仓储角色的用户", channel, message));

    }
}
