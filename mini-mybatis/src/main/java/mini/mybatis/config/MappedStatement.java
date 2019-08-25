package mini.mybatis.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class MappedStatement {
	
	private String namespace;
	
	private String sourceId;
	
	private String sql;
	
	private String resultType;
}