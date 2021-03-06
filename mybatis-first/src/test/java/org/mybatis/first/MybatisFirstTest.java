package org.mybatis.first;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.first.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MybatisFirstTest {

	@Test
	public void sqlSessionTest() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 构建sqlSession工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 获取sqlSession实例
		SqlSession sqlSession = sessionFactory.openSession();
		
		try {
			User u = sqlSession.selectOne("org.mybatis.first.entity.selectUserById", 1);
			log.info(u.toString());
		} finally {
			sqlSession.close();
		}
	}
}
