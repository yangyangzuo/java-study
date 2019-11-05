package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import book.download.DataSourceConnection;
import vo.Book;

public class BookDao {
	
	public boolean addBook(Book book) {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DataSourceConnection.getConnection();
			ps = connection.prepareStatement("insert into book values (null,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, book.getDxnumber());
			ps.setString(2, book.getFenlei());
			ps.setString(3,book.getNumberCategoryId());
			ps.setString(4,book.getBookName());
			ps.setString(5,book.getAuthor());
			ps.setInt(6,book.getPages());
			ps.setString(7,book.getPress());
			ps.setInt(8, book.getPublishYear());
			ps.setString(9, book.getPublishMoth());
			ps.setString(10,book.getIsbn());
			ps.setFloat(11, book.getPrice());
			ps.setString(12,book.getBookDesc());
			if(1==ps.executeUpdate()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceConnection.close(rs, ps, connection);
		}
		return flag;
	}

	//通过booknameid查找图书是否已经在数据库中存在
	public Book getBookByDxnumber(String dxNumber) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		try {
			connection = DataSourceConnection.getConnection();
			ps = connection.prepareStatement("select * from book where dxnumber=?");
			ps.setString(1, dxNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("id"));
				book.setDxnumber(dxNumber);
				book.setFenlei(rs.getString("fenlei"));
				book.setNumberCategoryId(rs.getString("numberCategoryId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor("author");
				book.setPages(rs.getInt("pages"));
				book.setPress(rs.getString("press"));
				book.setPublishYear(rs.getInt("publishYear"));
				book.setPublishMoth(rs.getString("publishMoth"));
				book.setIsbn(rs.getString("isbn"));
				book.setPrice(rs.getFloat("price"));
				book.setBookDesc(rs.getString("bookDesc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceConnection.close(rs, ps, connection);
		}
		return book;
	}
}
