package org.mybatis.first.mapper;

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
}
