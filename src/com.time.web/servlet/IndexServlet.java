package com.time.web.servlet;

import com.time.pojo.Book;
import com.time.service.BookService;
import com.time.service.impl.BookServiceImpl;
import com.time.utils.Constants;
import com.time.utils.JedisUtils;
import com.time.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/indexServlet")
public class IndexServlet extends BaseServlet {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookService service = new BookServiceImpl();
        Jedis jedis = JedisUtils.getJedis();

        List<Book> newBook = JSONArray.toList(JSONArray.fromObject(jedis.get(Constants.NEW_BOOK)), Book.class);
        List<Book> hotBook = JSONArray.toList(JSONArray.fromObject(jedis.get(Constants.HOT_BOOK)), Book.class);
        if (newBook.size() <= 1) {
            newBook = service.findNewBook();
            jedis.set(Constants.NEW_BOOK, JSONArray.fromObject(newBook).toString());

        }
        if (hotBook.size() <= 1) {
            hotBook = service.findHotBook();
            jedis.set(Constants.HOT_BOOK, JSONArray.fromObject(hotBook).toString());
        }
        jedis.close();
        request.setAttribute("newBook", newBook);
        request.setAttribute("hotBook", hotBook);
        return "index";
    }
}
