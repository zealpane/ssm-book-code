package org.mybatis.first;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.first.entity.User;
import org.mybatis.first.mapper.UserMapper;

public class MapperTest {

	public static void main( String[] args ) throws IOException
    {
    	String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 构建sqlSession工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 获取sqlSession实例
		SqlSession sqlSession = sessionFactory.openSession();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User u = userMapper.selectUserById(1);
		if (u != null) {				
			System.out.println(u);
		} else {
			System.out.println("用戶不存在");
		}
    }
}
