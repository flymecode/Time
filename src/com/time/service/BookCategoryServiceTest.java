package com.time.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.time.mapper.BookCategoryMapper;
import com.time.pojo.Book;
import com.time.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class BookCategoryServiceTest {

    @Test
    public void findBookCategory() {
    }

    @Test
    public void findBookByTypeId() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        BookCategoryMapper mapper = sqlSession.getMapper(BookCategoryMapper.class);
        PageHelper.startPage(1,2);
        List<Book> list = mapper.findBook(1);
        System.out.println(list.size());
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getTotal());
    }
}