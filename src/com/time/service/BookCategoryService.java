package com.time.service;

import com.time.pojo.Book;
import com.time.pojo.Category;

import java.util.List;

/**
 * 查找图书分类接口
 */
public interface BookCategoryService {

    /**
     * 查找图书所有的分类
     * @return
     */
    List<Category> findBookCategory();

    /**
     * 根据TypeId查找书籍
     * @param typeId
     * @return
     */
    List<Book> findBookByTypeId(int typeId);
}
