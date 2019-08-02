package com.whx.config.shiroSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/7/29.
 */
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisSessionDao extends AbstractSessionDAO {
    // Session超时时间，单位为毫秒
    private long expireTime = 120000;
   /* @Autowired
    private JedisCluster jedisCluster;*/
    @Autowired
    private RedisTemplate redisTemplate;// Redis操作类，对这个使用不熟悉的，可以参考前面的博客
    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("===============doCreate================");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        //jedisCluster.setex(ObjectMapperUtil.toJson(session.getId()),7*24*3600, ObjectMapperUtil.toJson(session));
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override//Serializable sessionId
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("==============doReadSession=================");
        if (sessionId == null) {
            System.out.println("serializable == null");
            return null;
        }
        return (Session) redisTemplate.opsForValue().get(sessionId);
        /*String toJson = ObjectMapperUtil.toJson(sessionId);
        if (toJson==null){
            System.out.println("toJson==null");
            return null;
        }
        String s = jedisCluster.get(toJson);
        if (s ==null){
            System.out.println("s==null");
            return null;
        }
        Session session = ObjectMapperUtil.toObject(s, Session.class);
        if (session==null){
            System.out.println("session==null");
            return null;
        }
        return session;*/
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        System.out.println("===============update================");
        if (session == null || session.getId() == null) {
            return;
        }
        session.setTimeout(expireTime);
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
        //jedisCluster.setex(ObjectMapperUtil.toJson(session.getId()),7*24*3600, ObjectMapperUtil.toJson(session));
    }

    @Override
    public void delete(Session session) {
        System.out.println("===============delete================");
        if (null == session) {
            return;
        }
        redisTemplate.opsForValue().getOperations().delete(session.getId());
        //jedisCluster.del(ObjectMapperUtil.toJson(session.getId()));
    }
    // 获取活跃的session，可以用来统计在线人数，如果要实现这个功能，可以在将session加入redis时指定一个session前缀，
    // 统计的时候则使用keys("session-prefix*")的方式来模糊查找redis中所有的session集合
    @Override
    public Collection<Session> getActiveSessions() {
        System.out.println("==============getActiveSessions=================");
        return redisTemplate.keys("*");
        //return null;
    }
}
