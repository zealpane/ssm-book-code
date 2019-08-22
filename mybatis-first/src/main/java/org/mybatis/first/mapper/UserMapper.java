package org.mybatis.first.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.first.entity.User;
/**
 * 与用户操作相关 的mapper接口
 */
public interface UserMapper {

	/**
	 * 根据用户id查询用户信息
	 * @return
	 */
	public User selectUserById(Integer id);
	
	public void insertUserBatch(@Param("userList") List<User> userList);
	
	public void insertUser2(User user);
	
	public void insertUserWithParam(@Param("user") User user);
	
	/**
	 * 按id删除
	 */
	public void deleteUserById(Integer id);
	/**
	 * 修改
	 */
	public void updateUserById(User user);
	/**
	 * 查询
	 */
}
