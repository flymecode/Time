package com.time.web.servlet;

import com.time.pojo.Book;
import com.time.pojo.Category;
import com.time.service.BookCategoryService;
import com.time.service.impl.BookCategoryServiceImpl;
import com.time.utils.Constants;
import com.time.utils.JedisUtils;
import com.time.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 图书分类
 */
@WebServlet("/bookCategoryServlet")
public class BookCategoryServlet extends BaseServlet {

    public String findCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Jedis jedis = null;
        try {
            BookCategoryService service = new BookCategoryServiceImpl();
            response.setContentType("application/json;charset=utf-8");
            // 在Redis获取全部信息
            jedis = JedisUtils.getJedis();
            final String allCategory = jedis.get(Constants.ALL_CATEGORY);
            if (allCategory == null || allCategory.equals("")) {
                List<Category> list = service.findBookCategory();
                String jsonStr = JSONArray.fromObject(list).toString();
                jedis.set(Constants.ALL_CATEGORY, jsonStr);
                response.getWriter().print(jsonStr);
            } else {
                response.getWriter().print(allCategory);
            }
        } catch (Exception e) {
            System.out.println("jedis这里报错了");
        } finally {
            JedisUtils.closeJedis(jedis);
        }
        return null;
    }

    public String findBookByTypeId(HttpServletRequest request, HttpServletResponse response) {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        BookCategoryService bookService = new BookCategoryServiceImpl();
        int page = 1;
        int rows = 2;
        //PageInfo<Book> pageInfo = bookService.findBookByTypeId(typeId, page, rows);
        List<Book> list = bookService.findBookByTypeId(typeId);
        request.setAttribute("list", list);
        return "list";
    }
}
