package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import book.download.DataSourceConnection;
import vo.Category;

public class CategoryDao {

	public boolean addCategory(Category category){
		boolean result = false;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DataSourceConnection.getConnection();
			ps = connection
					.prepareStatement("insert into category values (null,?,?,?,?,?)");
			ps.setString(1,category.getStringCategoryId());
			ps.setInt(2, category.getCategoryLevel());
			ps.setString(3, category.getNumberCategoryId());
			ps.setString(4, category.getParentNumberCategoryId());
			ps.setString(5, category.getCategoryName());
			if(1==ps.executeUpdate()){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceConnection.close(rs, ps, connection);
		}
		return result;
	}
	
	
	public Category getCategoryById(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;
		try {
			connection = DataSourceConnection.getConnection();
			ps = connection
					.prepareStatement("select * from category where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setId(rs.getInt("id"));
				category.setStringCategoryId(rs.getString("stringCategoryId"));
				category.setCategoryLevel(rs.getInt("categoryLevel"));
				category.setNumberCategoryId(rs.getString("numberCategoryId"));
				category.setParentNumberCategoryId(rs.getString("parentNumberCategoryId"));
				category.setCategoryName(rs.getString("CategoryName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceConnection.close(rs, ps, connection);
		}
		return category;
	}
	
	
	public Category getCategoryByStringCategoryId(String stringCategoryId) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;
		try {
			connection = DataSourceConnection.getConnection();
			ps = connection
					.prepareStatement("select * from category where stringCategoryId=?");
			ps.setString(1, stringCategoryId);
			rs = ps.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setId(rs.getInt("id"));
				category.setStringCategoryId(rs.getString("stringCategoryId"));
				category.setCategoryLevel(rs.getInt("categoryLevel"));
				category.setNumberCategoryId(rs.getString("numberCategoryId"));
				category.setParentNumberCategoryId(rs.getString("parentNumberCategoryId"));
				category.setCategoryName(rs.getString("CategoryName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceConnection.close(rs, ps, connection);
		}
		return category;
	}
	
	
	public Category getCategoryByNumberCategoryId(String numberCategoryId) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;
		try {
			connection = DataSourceConnection.getConnection();
			ps = connection
					.prepareStatement("select * from category where numberCategoryId=?");
			ps.setString(1, numberCategoryId);
			rs = ps.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setId(rs.getInt("id"));
				category.setStringCategoryId(rs.getString("stringCategoryId"));
				category.setCategoryLevel(rs.getInt("categoryLevel"));
				category.setNumberCategoryId(rs.getString("numberCategoryId"));
				category.setParentNumberCategoryId(rs.getString("parentNumberCategoryId"));
				category.setCategoryName(rs.getString("CategoryName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceConnection.close(rs, ps, connection);
		}
		return category;
	}
	
	
	//查询图书的所属分类
	//1.判断字符分类中是否含有;例如：TP393.092;TP312,如果含有;则拆分，逐个查询，如果查询到，则返回，不再进行其他查询
	//2.如果通过字符分类查询到不到分类，则根据数字分类进行查询，当前数字分类如果查询不到，则递归父数字分类递归查询，直到查询到结果为止
	//3.如果上面两个都未查询到分类结果，则返回null
	public Category getCategoryOfBook(String stringCategoryId,String numberCategoryId){
		Category category = null;
		//1.判断字符分类中是否含有;例如：TP393.092;TP312,如果含有;则拆分，逐个查询，如果查询到，则返回，不再进行其他查询
		String []stringCategoryIds;
		if(stringCategoryId != null){
			stringCategoryIds = stringCategoryId.split(";");
			for(int i=0;i<stringCategoryIds.length;i++){
				category = getCategoryByStringCategoryId(stringCategoryId);
				if(category != null){
					return category;
				}
			}
		}
		//2.如果通过字符分类查询到不到分类，则根据数字分类进行查询，当前数字分类如果查询不到，则递归父数字分类递归查询，直到查询到结果为止
		if(numberCategoryId != null){
			int i = 0;
			while(i != numberCategoryId.length()){
				category = getCategoryByNumberCategoryId(numberCategoryId.substring(0,numberCategoryId.length()-i));
				if(category != null){
					return category;
				}
				i = i + 2;
			}
		}
		//3.如果上面两个都未查询到分类结果，则返回null
		return category;
	}
	
	//查询图书的所属分类列表，冲最顶级分类，到当前图书分类
	public List<Category> getCategoriesOfBook(String stringCategoryId,String numberCategoryId){
		List<Category> categories = null;
		
		return categories;
	}
	
	

//	public List<FenLei> getCategories() {
//		Connection connection = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		List<FenLei> categories = new ArrayList<FenLei>();
//		try {
//			connection = DataSourceConnection.getConnection();
//			ps = connection.prepareStatement("select * from category");
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				FenLei c = new FenLei();
//				c.setId(rs.getInt("id"));
//				c.setName(rs.getString("name"));
//				categories.add(c);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DataSourceConnection.close(rs, ps, connection);
//		}
//		return categories;
//	}
}
