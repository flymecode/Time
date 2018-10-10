package com.time.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtils {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("SqlMapingConfig.xml");
			sqlSessionFactory = sfb.build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取单例SqlSessionFactory
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
