package com.mccoy.yourstatus.web.util;

import com.mccoy.yourstatus.service.Impl.UserFollowServiceImpl;
import com.mccoy.yourstatus.service.Impl.UserServiceImpl;
import com.mccoy.yourstatus.service.Impl.UserStatusMessageServiceImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.UUID;
import java.util.logging.Logger;

public class ServiceUtil {
    private static Logger LOG = Logger.getLogger(ServiceUtil.class.getName());
    private static InitialContext JNDI_CONTEXT;

    static {
        try {
            JNDI_CONTEXT = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private String uid = UUID.randomUUID().toString();

    public static UserServiceImpl getUserService() {
        UserServiceImpl svr = null;
        try {
            svr = (UserServiceImpl) JNDI_CONTEXT.lookup("java:global/YourStatus-1.0-SNAPSHOT/UserServiceImpl!com.mccoy.yourstatus.service.GenericDAO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return svr;
    }

    public static UserFollowServiceImpl getUserFollowService() {
        UserFollowServiceImpl svr = null;
        try {
            svr = (UserFollowServiceImpl) JNDI_CONTEXT.lookup("java:global/YourStatus-1.0-SNAPSHOT/UserFollowServiceImpl!com.mccoy.yourstatus.service.GenericDAO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return svr;
    }

    public static UserStatusMessageServiceImpl getUserStatusMessageService() {
        UserStatusMessageServiceImpl svr = null;
        try {
            svr = (UserStatusMessageServiceImpl) JNDI_CONTEXT.lookup("java:global/YourStatus-1.0-SNAPSHOT/UserStatusMessageServiceImpl!com.mccoy.yourstatus.service.GenericDAO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return svr;
    }

}
