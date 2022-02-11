package com.mccoy.yourstatus.web.util;

import com.mccoy.yourstatus.service.Impl.UserFollowServiceImpl;
import com.mccoy.yourstatus.service.Impl.UserServiceImpl;
import com.mccoy.yourstatus.service.Impl.UserStatusMessageServiceImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.UUID;
import java.util.logging.Logger;

public class ServiceUtil {
    private String uid = UUID.randomUUID().toString();
    private static Logger LOG = Logger.getLogger(ServiceUtil.class.getName());

    public static UserServiceImpl getUserService() {
        UserServiceImpl svr = null;
        try {
            InitialContext jndi = new InitialContext();
            svr = (UserServiceImpl) jndi.lookup("java:global/YourStatus-1.0-SNAPSHOT/UserServiceImpl!com.mccoy.yourstatus.service.GenericDAO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return svr;
    }

    public static UserFollowServiceImpl getUserFollowService() {
        UserFollowServiceImpl svr = null;
        try {
            InitialContext jndi = new InitialContext();
            svr = (UserFollowServiceImpl) jndi.lookup("java:global/YourStatus-1.0-SNAPSHOT/UserFollowServiceImpl!com.mccoy.yourstatus.service.GenericDAO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return svr;
    }

    public static UserStatusMessageServiceImpl getUserStatusMessageService() {
        UserStatusMessageServiceImpl svr = null;
        try {
            InitialContext jndi = new InitialContext();
            svr = (UserStatusMessageServiceImpl) jndi.lookup("java:global/YourStatus-1.0-SNAPSHOT/UserStatusMessageServiceImpl!com.mccoy.yourstatus.service.GenericDAO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return svr;
    }

}
