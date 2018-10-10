package com.time.mapper;

import com.time.pojo.Book;
import com.time.pojo.Category;

import java.util.List;

public interface BookCategoryMapper {
    /**
     * 查找图书分类
     * @return
     */
    List<Category> findCategory();

    /**
     * 根据TypeId查询书籍
     * @param bookTypeId
     * @return
     */
    List<Book> findBook(int bookTypeId);
}
