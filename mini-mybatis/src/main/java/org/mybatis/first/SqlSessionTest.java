package org.mybatis.first;

import java.io.IOException;

import org.mybatis.first.entity.User;

import mini.mybatis.sqlSession.SqlSession;
import mini.mybatis.sqlSession.SqlSessionFactory;

/**
 * 用传统的sqlSession操作数据库
 *
 */
public class SqlSessionTest 
{
    public static void main( String[] args ) throws IOException
    {
    	// 构建sqlSession工厂，参数为xml映射文件所在的包
		SqlSessionFactory sessionFactory = new SqlSessionFactory("org.mybatis.first.mapper.xml");
		// 获取sqlSession实例
		SqlSession sqlSession = sessionFactory.openSession();
		
		try {
			Integer[] userId = {1};
			User u = sqlSession.selectOne("org.mybatis.first.mapper.UserMapper.selectUserById", userId);
			if (u != null) {
				System.out.println(u);
			} else {
				System.out.println("用戶不存在");
			}
		} finally {
//			sqlSession.close();
		}
    }
}
