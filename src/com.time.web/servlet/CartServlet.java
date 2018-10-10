package com.time.web.servlet;

import com.time.pojo.Book;
import com.time.pojo.Cart;
import com.time.pojo.Item;
import com.time.pojo.User;
import com.time.service.BookService;
import com.time.service.CartService;
import com.time.service.impl.BookServiceImpl;
import com.time.service.impl.CartServiceImpl;
import com.time.utils.Constants;
import com.time.utils.CookieUtils;
import com.time.web.base.BaseServlet;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;

/**
 * 购物车模块
 */
@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet {

    private CartService cartService = null;
    private BookService bookService = null;


    public String getBookNumber(HttpServletRequest request, HttpServletResponse respon) throws Exception {

        Cart cart = null;
        // 先从cookie取
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(NON_NULL);
        Cookie[] cookies = request.getCookies();
        Cookie cook = CookieUtils.getCookieByName(Constants.COOKIE_CART, cookies);

        if (null != cook) {
            cart = om.readValue(URLDecoder.decode(cook.getValue(), "utf-8"), Cart.class);
            if (cart != null && cart.getItems().size() > 0) {
                respon.getWriter().write(cart.getBookNumber().toString());
                return null;
            }
        }
        // 判断用户是否登陆
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if (null != user) {
            cart = cartService.findCartFromRedis(user.getUserName());
            if (cart != null && cart.getItems().size() > 0) {
                respon.getWriter().write(cart.getBookNumber().toString());
                return null;
            }
        }

        return null;
    }
    /**
     * 加入购物车
     *
     * @param request
     * @param respon
     * @return
     * @throws Exception
     */
    public String addCart(HttpServletRequest request, HttpServletResponse respon) throws Exception {
        cartService = new CartServiceImpl();
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(NON_NULL);
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Integer bookNumber = Integer.parseInt(request.getParameter("bookNumber"));
        Integer bookPrice = Integer.parseInt(request.getParameter("bookPrice"));

        Cart cart = null;
        Cookie[] cookies = request.getCookies();
        Cookie cook = CookieUtils.getCookieByName(Constants.COOKIE_CART, cookies);
        if (null != cook) {
            cart = om.readValue(URLDecoder.decode(cook.getValue(), "utf-8"), Cart.class);
        } else {
            cart = new Cart();
        }

        if (null != bookId && null != bookNumber) {
            Item item = new Item();
            Book book = new Book();
            book.setBookId(bookId);
            book.setPrice(bookPrice);
            item.setBook(book);
            item.setBookNumber(bookNumber);
            cart.addItem(item);
        }
        /**
         * 排序
         */
        List<Item> items = cart.getItems();
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return -1;
            }
        });

        /**
         * 判断用户是否登陆
         */
        User user = (User) request.getSession().getAttribute("userName");
        if (null != user) {
            cartService.addCartToRedis(user.getUserName(), cart);
            Cookie cookie = CookieUtils.addCookie(Constants.COOKIE_CART, null);
            respon.addCookie(cookie);
        } else {
            Writer w = new StringWriter();
            om.writeValue(w, cart);
            System.out.println(w.toString());
            String json = URLEncoder.encode(w.toString(), "utf-8");
            Cookie cookie = new Cookie(Constants.COOKIE_CART, json);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
            respon.addCookie(cookie);
        }
        return null;
    }

    /**
     * 去购物车结算
     */
    public String toCart(HttpServletRequest request, HttpServletResponse respon) throws IOException {
        bookService = new BookServiceImpl();
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(NON_NULL);
        Cart cart = null;
        // 获取cookie中的购物车
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (Constants.COOKIE_CART.equals(cookie.getName())) {
                    //购物车 对象 与json字符串互转
                    cart = om.readValue(URLDecoder.decode(cookie.getValue(), "utf-8"), Cart.class);
                    break;
                }
            }
        }

        // 判断是否登陆
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if (null != user) {
            if (null != cart) {
                cartService.addCartToRedis(user.getUserName(), cart);
                Cookie cookie = CookieUtils.addCookie(Constants.COOKIE_CART, null);
                respon.addCookie(cookie);
            }
            cart = cartService.findCartFromRedis(user.getUserName());
        }
        if (null == cart) {
            cart = new Cart();
        }
        List<Item> items = cart.getItems();
        if (items.size() > 0) {
            //只有购物车中有购物项, 才可以将bookid相关信息加入到购物项中
            for (Item item : items) {
                item.setBook(bookService.findBookById(item.getBook().getBookId()));
            }
        }
        request.setAttribute("cart", cart);
        return "cart";
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */

    // TODO 缺少判断存货
    public String toBuy(HttpServletRequest request, HttpServletResponse response){
        String[] bookIds = null;
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        Cart cart = cartService.selectCartFromRedisByBookIds(bookIds, user.getUserName());
        List<Item> items = cart.getItems();
        if (items.size() > 0) {

        }else {
            return "redirect:/shopping/toCart";
        }
        return "order";
    }





}
