package mini.mybatis.executor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface StatementHandler {

	PreparedStatement prepare(Connection connection);
	
	ResultSet query(PreparedStatement statement);
}
