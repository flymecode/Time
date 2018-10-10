package com.time.utils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class SessionMapUtils implements HttpSessionListener ,HttpSessionAttributeListener{
    /**
     * 用户和Session绑定关系
     */
    public static final Map<String, HttpSession> USER_SESSION = new HashMap<String, HttpSession>();

    /**
     * seeionId和用户的绑定关系
     */
    public static final Map<String, String> SESSIONID_USER = new HashMap<String, String>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        //当前session销毁时删除当前session绑定的用户信息
        //同时删除当前session绑定用户的HttpSession
        USER_SESSION.remove(SESSIONID_USER.remove(sessionId));

    }

    /**
     * 用户登录时的处理
     * 处理一个账号同时只有一个地方登录的关键
     *
     * @param request
     */
    public static void userLoginHandle(HttpServletRequest request) {
        //当前登录的用户
        String userName = request.getParameter("userName");
        //当前sessionId
        String sessionId = request.getSession().getId();
        //删除当前sessionId绑定的用户，用户--HttpSession
        USER_SESSION.remove(SESSIONID_USER.remove(sessionId));
        //删除当前登录用户绑定的HttpSession
        HttpSession session = USER_SESSION.remove(userName);
        if (session != null) {
            SESSIONID_USER.remove(session.getId());
            //session.invalidate();
            System.out.println(session.getId());
            session.removeAttribute("userName");
            session.setAttribute("userMsg", "您的账号已经在另一处登录了,你被迫下线!");

        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println(httpSessionBindingEvent.getSession().getId());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
