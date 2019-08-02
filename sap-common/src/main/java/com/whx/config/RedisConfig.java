package com.whx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2019/7/11.
 */
@Configuration//标识是一个配置类
@PropertySource("classpath:/properties/redis.properties")
@Lazy         //延迟加载
public class RedisConfig {
    @Value("${redis.nodes}")
    String nodes;
    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> set = getSet();
        return new JedisCluster(set);
    }

    private Set<HostAndPort> getSet() {
        Set<HostAndPort> set = new HashSet<>();
        String[] nodess = nodes.split(":");
        for (String nodes:nodess) {
            String[] hostAndPort = nodes.split(",");
            set.add(new HostAndPort(hostAndPort[0],Integer.parseInt(hostAndPort[1])));
        }
        return set;
    }

}
