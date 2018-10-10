package com.time.service;

import com.time.pojo.Book;

import java.util.List;

/**
 * 书籍接口
 */
public interface BookService {
    /**
     * 根据isbn查找
     * @param isbn
     * @return
     */
    Book findBookByIsbn(String isbn);

    /**
     * 根据书名查找书籍
     * @param bookName
     * @return
     */
    Book findBookByName(String bookName);

    /**
     * 根据Id查询书籍
     * @param id
     * @return
     */
    Book findBookById(Integer id);

    /**
     * 查找最新书籍
     * @return
     */
    List<Book> findNewBook();

    /**
     * 查找热卖书籍
     * @return
     */
    List<Book> findHotBook();
}
