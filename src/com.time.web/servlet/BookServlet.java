package com.time.web.servlet;

import com.time.pojo.Book;
import com.time.service.BookService;
import com.time.service.impl.BookServiceImpl;
import com.time.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookServlet")
public class BookServlet extends BaseServlet {

    public String search(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("search");
        BookService bookService = new BookServiceImpl();
        Book book = null;

        if (null == search || search.trim() == "") {
            request.setAttribute("msg", "输入错误");
            return "msg";
        }

        if(search.matches("[0-9]{13}")){
            book = bookService.findBookByIsbn(search);
        }else{
            book = bookService.findBookByName(search);
        }
        if(book != null){
            request.setAttribute("book", book);
            return "bookInfo";
        } else {
            request.setAttribute("msg", "抱歉未查找到此书");
            return "msg";
        }


    }

    public String findBookById(HttpServletRequest request, HttpServletResponse response) {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookService bookService = new BookServiceImpl();
        Book book = (Book) bookService.findBookById(bookId);
        request.setAttribute("book", book);
        return "bookInfo";
    }

}
