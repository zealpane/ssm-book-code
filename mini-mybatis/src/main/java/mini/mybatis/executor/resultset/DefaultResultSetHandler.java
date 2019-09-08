package mini.mybatis.executor.resultset;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mini.mybatis.config.MappedStatement;

public class DefaultResultSetHandler implements ResultSetHandler {

	private MappedStatement mappedStatement;
	
	public DefaultResultSetHandler(MappedStatement mappedStatement) {
		this.mappedStatement = mappedStatement;
	}

	@Override
	public <E> List<E> handleResultSets(ResultSet resultSet) throws SQLException {
		if (resultSet == null) return null;
		List<E> ret = new ArrayList<E>();
		String className = mappedStatement.getResultType();
		Class<?> returnClass = null;
		while(resultSet.next()) {
				E entry = null;
				try {
					returnClass = Class.forName(className);
					entry = (E)returnClass.newInstance();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Field[] declaredFields = returnClass.getDeclaredFields();
				for (Field field : declaredFields) {
					String fieldName = field.getName();
					// 字段数据类型
					String fieldType = field.getType().getSimpleName();
					
					try {
						field.setAccessible(true);
						// 如果是整型
						switch (fieldType) {
						case "String":
							field.set(entry, resultSet.getString(fieldName));
							break;
						case "Integer":
							field.set(entry, resultSet.getInt(fieldName));
							break;
						default:
							field.set(entry, resultSet.getString(fieldName));
							break;
						}
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ret.add(entry);
				}
		}
		return ret;
	}

}
