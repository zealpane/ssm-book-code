package jdbcDemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {

	public static void main(String[] args) {
		// 声明使用jdbc过程中几个必要的变量，并先初始化为null；这里要注意引用的几个类都是java.sql包下的
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// 加载mysql jdbc驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取数据库连接
			conn = DriverManager.getConnection("jdbc:mysql://192.168.167.179:3306/mybatis-demo", "root", "kzxkdzt007!");
			// 定义sql语句
			String selectUserSql = "select * from g_ac_user where id = ?";
			// 获取预处理statement
			preparedStatement = conn.prepareStatement(selectUserSql);
			// 设置参数，第一个参数为参数的索引，用来确定是哪个参数，索引值从1开始；第二个参数为指定位置要设置的值，用来确定对应参数是什么值
			preparedStatement.setLong(1, 1L);
			// 查询结果集
			resultSet = preparedStatement.executeQuery();
			StringBuilder sr = new StringBuilder("id为1的用户信息：\n");
			while (resultSet.next()) {
				String email = resultSet.getString("email");
				String name = resultSet.getString("name");
				sr.append("邮箱：").append(email).append("-----------姓名：").append(name);
			}
			System.out.println(sr);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭释放资源，遵循先开后闭的原则
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
