package com.time.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    /**
     * 通过名称在cookie数组获取指定的cookie
     *
     * @param name    cookie名称
     * @param cookies cookie数组
     * @return
     */
    public static Cookie getCookieByName(String name, Cookie[] cookies) {
        if (null != cookies && cookies.length > 0) {
            for (Cookie c : cookies) {
                //通过名称获取
                if (name.equals(c.getName())) {
                    //返回
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 写入
     */
    public static Cookie addCookie(String key,String value){
        Cookie cookie = new Cookie(Constants.COOKIE_CART, null);
        cookie.setPath("/");
        cookie.setMaxAge(-0);
        return cookie;
    }
}
