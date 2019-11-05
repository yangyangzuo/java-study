package book.download;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import dao.BookDao;

/**
 * 开源basicDataSoure数据源的使用
 * 
 * 用的是java.sql.DataSource接口的一个开源实现,BasicDataSource,BasicDataSource定义了连接的详细信息
 * 
 * 优点:
 * 
 * 通过DataSource获得Connection速度很快(数据源包含了连接池),数据源就是连接池
 * 
 * 通过DataSource获得的Connection都是已经被包裹过的（不是驱动原来的连接），他的close方法已经被修改
 * 和自己手动模式下的连接池是一个原理
 * 
 * 一般DataSource内部会用一个连接池来缓存Connection，这样可以大幅度提高数据库的访问速度
 * 
 * 连接池可以理解成一个能够存放Connection的Collection
 * 
 * @author Administrator
 * 
 */
public class DataSourceConnection {
	private static Properties properties = new Properties();
	private static DataSource dataSource = null;
	static {
		try {
			InputStream is = DataSourceConnection.class.getClassLoader()
					.getResourceAsStream("dataSourceConfig.properties");
			properties.load(is);
			is.close();
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			System.out.println("数据库连接错误");
		}

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}

	public static void rollBack(Connection connection) throws SQLException {
		connection.rollback();
	}

	public static void close(ResultSet rs, Statement ps, Connection connection) {
		try {
			try {
				if (rs != null)
					rs.close();

			} finally {
				try {
					if (ps != null)
						ps.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) throws SQLException{
		BookDao b = new BookDao();
		b.addBook(null);
		
	}
}