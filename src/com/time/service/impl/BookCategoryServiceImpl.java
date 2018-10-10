package com.time.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.time.mapper.BookCategoryMapper;
import com.time.pojo.Book;
import com.time.pojo.Category;
import com.time.service.BookCategoryService;
import com.time.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class BookCategoryServiceImpl implements BookCategoryService {

    @Override
    public List<Category> findBookCategory() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookCategoryMapper mapper = sqlSession.getMapper(BookCategoryMapper.class);
        List<Category> list = mapper.findCategory();
        return list;
    }

    @Override
    public List<Book> findBookByTypeId(int typeId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookCategoryMapper mapper = sqlSession.getMapper(BookCategoryMapper.class);
        PageHelper.startPage(1,2);
        List<Book> list = mapper.findBook(typeId);
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getTotal());
        return list;
    }
}
