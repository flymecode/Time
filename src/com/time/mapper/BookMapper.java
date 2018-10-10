package com.time.mapper;

import com.time.pojo.Book;

import java.util.List;

public interface BookMapper {
    /**
     * 根据isbn查找isbn
     * @param isbn
     * @return
     */
    Book findBookByIsbn(String isbn);

    /**
     * 根据书籍名称查询
     * @param BookName
     * @return
     */
    Book findBookByName(String BookName);

    /**
     * 根据书籍Id查询
     */
    Book findBookById(Integer bookId);

    /**
     * 根据权重查找
     * @return
     */
    List<Book> findBookByWeight();

    /**
     * 根据时间查找
     * @return
     */
    List<Book> findBookByTime();
}
