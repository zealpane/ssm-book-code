package mini.mybatis.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class MappedStatement {
	// 命名空间
	private String namespace;
	// 标签id
	private String sourceId;
	// 标签内容，即sql语句
	private String sql;
	// 返回值类型
	private String resultType;
}