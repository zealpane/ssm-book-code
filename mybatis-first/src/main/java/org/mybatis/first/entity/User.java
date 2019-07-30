package org.mybatis.first.entity;

import lombok.Data;

/**
 * 用户实体类
 * @author 泽朋
 *
 */
@Data
public class User {

	private Integer id;
	private String username;
	private String email;
	private String password;
	
}
