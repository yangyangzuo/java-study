package vo;

/**
 * 图书对象
 * 
 * @author Administrator
 * 
 */
public class Book {
	private int id;
	private String dxnumber;
	private String fenlei;
	// -- 这个字段和category中的numberCategoryId对应,当查询一个图书的分类的时候，根据这个字段查询该图书属于哪个分类
	// -- 这个字段根据fenlei的值计算而来，如果fenlei值过长，会进行缩短降级，匹配category表中的数值
	private String numberCategoryId;
	// -- 书名: 给生意人看的人脉经营书
	private String bookName;
	// -- 作者: 仁久
	private String author;
	// -- 页数
	private int pages;
	// -- 出版社: 中国华侨出版社
	private String press;
	// -- 出版时间: 2011年06月
	private int publishYear;
	// -- 出版月份:无数据，则为00
	private String publishMoth;
	// -- (ISBN编号)书号: 9787511314635
	private String isbn;
	// -- 价格
	private float price;
	// -- 图书简介
	private String bookDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDxnumber() {
		return dxnumber;
	}

	public void setDxnumber(String dxnumber) {
		this.dxnumber = dxnumber;
	}

	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getNumberCategoryId() {
		return numberCategoryId;
	}

	public void setNumberCategoryId(String numberCategoryId) {
		this.numberCategoryId = numberCategoryId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public String getPublishMoth() {
		return publishMoth;
	}

	public void setPublishMoth(String publishMoth) {
		this.publishMoth = publishMoth;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

}
