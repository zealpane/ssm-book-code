package org.mybatis.first;

import java.io.IOException;

import org.mybatis.first.entity.User;
import org.mybatis.first.mapper.UserMapper;

import mini.mybatis.sqlSession.SqlSession;
import mini.mybatis.sqlSession.SqlSessionFactory;

public class MapperTest {

	public static void main( String[] args ) throws IOException
    {
		// 构建sqlSession工厂，参数为xml映射文件所在的包
		SqlSessionFactory sessionFactory = new SqlSessionFactory("org.mybatis.first.mapper.xml");
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
