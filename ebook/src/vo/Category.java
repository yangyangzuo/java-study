package vo;

public class Category {
	private int id;
	// 字符分类标识
	private String stringCategoryId;
	// 分类等级
	private int categoryLevel;
	// 数字分类标识
	private String numberCategoryId;
	// 上级数字分类标识,1级分类的上级数字分类为
	private String parentNumberCategoryId;
	// 分类名称
	private String CategoryName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStringCategoryId() {
		return stringCategoryId;
	}

	public void setStringCategoryId(String stringCategoryId) {
		this.stringCategoryId = stringCategoryId;
	}

	public int getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getNumberCategoryId() {
		return numberCategoryId;
	}

	public void setNumberCategoryId(String numberCategoryId) {
		this.numberCategoryId = numberCategoryId;
	}

	public String getParentNumberCategoryId() {
		return parentNumberCategoryId;
	}

	public void setParentNumberCategoryId(String parentNumberCategoryId) {
		this.parentNumberCategoryId = parentNumberCategoryId;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

}
