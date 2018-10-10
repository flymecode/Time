package com.time.web.servlet;

import com.time.pojo.User;
import com.time.pojo.UserInfo;
import com.time.service.UserInfoService;
import com.time.service.UserService;
import com.time.service.impl.UserInfoServiceImpl;
import com.time.service.impl.UserServiceImpl;
import com.time.utils.*;
import com.time.web.base.BaseServlet;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String checkUserName(HttpServletRequest request , HttpServletResponse response) throws Exception {
        String userName = request.getParameter("username");
        UserService userService = new UserServiceImpl();
        response.setContentType("application/json;charset=utf-8");
        boolean flag = userService.checkUserName(userName);

        if(flag){
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
        return null;
    }

    /**
     * 用户注册UI
     * @param request
     * @param response
     * @return
     */
    public String userRegistUI(HttpServletRequest request, HttpServletResponse response) {
        return "regist";
    }
    /**
     * 用户注册
     * @param request
     * @param response
     * @return
     */
    public String userRegist(HttpServletRequest request, HttpServletResponse response) throws MessagingException, SQLException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        MyBeanUtils.populate(user,map);
        user.setCode(UUIDUtils.getCode());
        user.setState(0);
        user.setCreateTime(new Date());
        UserService service = new UserServiceImpl();
        service.userRegist(user);
        try {
            // 注册成功向，用户邮发送信息，跳转到提示信息页面
            // 发送邮件
            EmailUitls.sendAccountActivateEmail(user);
            request.setAttribute("msg", "用户注册成功，请激活！" );
        } catch (Exception e) {
            // 注册失败，跳转到提示页面
            request.setAttribute("msg", "用户注册失败，请重新注册！");
        }
        return "msg";
    }

    /**
     * 邮箱激活
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String  activate (HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String code = request.getParameter("code");
        String checkCode = request.getParameter("checkCode");
        UserService userService = new UserServiceImpl();
        User user = userService.findUserByCode(code);
        boolean sure = GenerateLinkUtils.verifyCheckcode(user, checkCode);
        if(sure){
            userService.updateUserState(user);
            return "login";
        } else {
            request.setAttribute("msg","激活失败，请重新激活");
            return "msg";
        }
    }
    /**
     * 用户登陆UI
     *
     * @param request
     * @param response
     * @return
     */
    public String userLoginUI(HttpServletRequest request, HttpServletResponse response) {

        return "login";
    }

    /**
     * 查找用户个人信息
     *
     * @param request
     * @param response
     * @return
     */
    public String findUserInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        UserInfoService service = new UserInfoServiceImpl();
        UserInfo userInfo = service.findUserInfo(id);
        return null;
    }

    /**
     * 用户登陆
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public String userLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String autoLogin = request.getParameter("remember");
            User user = new User();
            MyBeanUtils.populate(user, request.getParameterMap());
            String temp = user.getPassWord();
            UserService userService = new UserServiceImpl();
            user = userService.userLogin(user);


            if (null != user) {
                //TODO
                if ("#".equals(autoLogin)) {
                    // 发送Cookie给客户端
                    Cookie cookie = new Cookie(Constants.COOKIE_USER, user.getUserName() + "#" + temp);
                    cookie.setMaxAge(60 * 60 * 24);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                    HttpSession session = request.getSession();
                    SessionMapUtils.userLoginHandle(request);
                    SessionMapUtils.USER_SESSION.put(user.getUserName().trim(), session);
                    SessionMapUtils.SESSIONID_USER.put(session.getId(),user.getUserName());
                    session.setAttribute(Constants.SESSION_USER, user);
                    response.sendRedirect("/index.jsp");
                } else {
                    // 用户登陆成功，将用户信息放入session
                    HttpSession session = request.getSession();
                    //处理用户登录(保持同一时间同一账号只能在一处登录)
                    SessionMapUtils.userLoginHandle(request);
                    //添加用户与HttpSession的绑定
                    SessionMapUtils.USER_SESSION.put(user.getUserName().trim(), session);
                    //添加sessionId和用户的绑定
                    SessionMapUtils.SESSIONID_USER.put(session.getId(),user.getUserName());
                    session.setAttribute(Constants.SESSION_USER, user);
                    // 用户登陆成功，将用户信息放入session
                    response.sendRedirect("/index.jsp");
                }

            }

        } catch (Exception e) {
            String msg = e.getMessage();
            // 放入失败的信息
            request.setAttribute("msg", msg);
            // 转发到登陆界面
            return "login";
        }
        return null;
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String userQuit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 清除session
        request.getSession().invalidate();
        // 重新定向到首页
        response.sendRedirect("/index.jsp");
        return null;
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String changePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService userService = new UserServiceImpl();
        String newPassword = request.getParameter("newPassword");
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        user.setPassWord(newPassword);
        Boolean result = userService.updateUser(user);
        if(result){
            return "login";
        }else{
            return "msg";
        }
    }
}
