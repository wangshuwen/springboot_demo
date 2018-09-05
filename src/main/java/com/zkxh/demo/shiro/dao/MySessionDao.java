package com.zkxh.demo.shiro.dao;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @ClassName MySessionDao
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/27 18:40
 * @Vserion v0.0.1
 */

//public class MySessionDao extends CachingSessionDAO {
public class MySessionDao {

    //    @Autowired
    private RedisSessionDAO redisSessionDAO;

//    @Override
//    protected void doUpdate(Session session) {
//        redisSessionDAO.update(session);
//    }
//
//    @Override
//    protected void doDelete(Session session) {
//        redisSessionDAO.delete(session);
//    }
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        return session.getId();
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        return redisSessionDAO.readSession(sessionId);
//    }
}
