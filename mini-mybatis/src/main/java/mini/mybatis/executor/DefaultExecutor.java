package mini.mybatis.executor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lombok.Data;
import mini.mybatis.config.Configuration;
import mini.mybatis.config.MappedStatement;
import mini.mybatis.executor.resultset.DefaultResultSetHandler;
import mini.mybatis.executor.resultset.ResultSetHandler;

@Data
public class DefaultExecutor implements Executor {

	private Configuration configuration;

	public DefaultExecutor(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException {
		// 1、.获取连接
		Connection conn = getConnection();

		// 获取预处理statement
		PreparedStatement preparedStatement = conn.prepareStatement(ms.getSql());

		if (parameter != null) {
			if (parameter.getClass().isArray()) {
				Object[] paramArray = (Object[]) parameter;
				int parameterIndex = 1;
				for (Object param : paramArray) {
					if (param instanceof Integer) {
						preparedStatement.setInt(parameterIndex, (int) param);
					} else if (param instanceof String) {
						preparedStatement.setString(parameterIndex, (String) param);
					}
					parameterIndex++;
				}
			}
		}
		// 查询结果集
		ResultSet resultSet = preparedStatement.executeQuery();

		// 对resultSet进行处理
		ResultSetHandler resultSetHandler = new DefaultResultSetHandler(ms);
		return resultSetHandler.handleResultSets(resultSet);
	}

	/**
	 * 获取数据库的连接，和jdbc一样的方式
	 * 
	 * @return
	 */
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(configuration.getJdbcDriver());
			connection = DriverManager.getConnection(configuration.getJdbcUrl(), configuration.getUsername(),
					configuration.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
