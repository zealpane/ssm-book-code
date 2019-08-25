package mini.mybatis.executor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mini.mybatis.config.MappedStatement;

public class DefaultStatementHandler implements StatementHandler {

	private MappedStatement mappedStatement;
	
	
	public DefaultStatementHandler(MappedStatement mappedStatement) {
		super();
		this.mappedStatement = mappedStatement;
	}

	@Override
	public PreparedStatement prepare(Connection connection) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement =  connection.prepareStatement(mappedStatement.getSql());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	@Override
	public ResultSet query(PreparedStatement statement) {
		ResultSet resultSet = null;
		try {
			resultSet =  statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

}
