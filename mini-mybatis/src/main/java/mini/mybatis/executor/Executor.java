package mini.mybatis.executor;

import java.sql.SQLException;
import java.util.List;

import mini.mybatis.config.MappedStatement;

public interface Executor {

	<E> List<E> query(MappedStatement ms,Object parameter) throws SQLException;
}
