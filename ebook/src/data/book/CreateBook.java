package data.book;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import book.download.ChuanDi;
import book.download.Connection;
import dao.BookDao;
import dao.CategoryDao;
import vo.Book;

/**
 * 数据抓取步骤：
 * 
 * 1.构造抓取url
 * 格式:search?channel=search&sw=javascript&ecode=utf-8&sectyear=2014&Pages=3
 * 其中:
 * channel=search	可以省略
 * &sw=javascript	不可省略,要查询的关键字
 * &ecode=utf-8		查询的编码,可以省略
 * &sectyear=2014	查询的特定年份的图书,不可以省略,如果省略，图书超过200页，数据无法抓取
 * &Pages=3			查询特定分页的图书,可以省略
 * 省略后的格式:search?sw=javascript
 * 
 * 2.添加抓取网站头,这里有两个免费的
 * http://book.ucdrs.superlib.net/
 * http://book.szdnet.org.cn/
 * 构造添加搜索头后的url为:
 * http://book.ucdrs.superlib.net/search?channel=search&sw=javascript&ecode=utf-8&sectyear=2014&Pages=3
 * http://book.szdnet.org.cn/search?channel=search&sw=javascript&ecode=utf-8&sectyear=2014&Pages=3
 * 
 * 3.请求构造好的url,比如：http://book.ucdrs.superlib.net/search?channel=search&sw=javascript&ecode=utf-8&sectyear=2014&Pages=3
 * 分析获得如下数据:
 * 	1.当前查询条件下，一共获得多少本图书
 * 	抓如内容格式：
 * 	<b>找到与 <font color=red> javascript</font> 相关的<font color=red>中文图书</font> 4533 种,用时 0.002 秒</b> 当前为第 1 页
 * 	获得一共所少本图书，计算当前图书一共可以划分为多少页，每页有10本
 * 	2.如果图书存在，间隔特定时间后，打开一个图书的url链接
 * 	抓取的内容格式：
 * 	<td height="20">（现判断这一个行是否满足这个，如果满足，则抓取下面的图书url链接，存入数组）
 *	<A class=px14 href="/views/specific/2929/bookDetail.jsp?dxNumber=000000099768&d=1459D10F9650A65648C2B8E3D9C6D0B8&fenlei=1817041103010901"
 * 
 * 4.逐本打开对应书的url,分析页面的内容
 * 
 * 
 * 代码执行流程:
 * 1.在浏览器种打开抓取的url,获得cookie
 * 2.打开Connection,修改cookie,然后执行该zhuaqutushu.java
 * 
 * 
 * 图书的保存说明:
 * 图书保存的时候，会抓取一个分类号
 * 得到分类号后，查询数据库，判断该分类号是否存在,如果存在，则按照该分类号所在的目录存储具体的内容图片
 * 如果该分类号，在数据库中不存在，则把该分类号降级，即：每次减少2位，再次判断该分类是否存在，如果存在，则保存到降级后的分类里面
 * 例如:一个分类时0102030405,如果该分类不存在，则降级为01020304,再次判断该分类是否存在，依次类推
 * 这样做，可以确保每一个图书一定会有一个具体的分类
 * 当在图书详情页面显示图书的中图法分类的时候，这里使用数据库中冲别的网站抓取的分类，不使用读秀种定义的中图法显示数据，他的不准确，而且有的数据不存在
 * 
 * 图书保存目录说明:
 * 1级分类号/2级分类号/.../books/dxnumber
 * 例如:
 * 18/1817/181704/18170403/1817040301/181704030101/books/000006706854
 * 最后一个dxnumber编号
 * @author zuoyang
 *
 */
public class CreateBook {
	//图书下载根目录
	public String dir = "/home/zuoyang/Desktop/test/";
	//抓取图书的urlhead
	public String urlHead = "http://book.ucdrs.superlib.net";
	public ArrayList books;
	//数据抓取延迟
	public int time = 5000;
	
	
	public static void main(String []args) throws Exception{
		
		//关键字,字符串,例如:javascript
		//抓取年份,年份可以为空串;为一个具体的年份，例如：2014；还有一个特殊的串，即100-1955,这个值表示图书在添加的时候，没有添加年份值
		//第几页数据,例如:1
		//当前页的第几条数据,例如:1
		
		
		//关键字
		//抓取条件:关键字和分类
		//抓取顺序:
		//1.现抓取工业技术(18)
		//2.a-z,A-Z,200个常用汉字
		
		new CreateBook().getHowManyBooks("java","2015","",15,1);
		
	}
	
	/**
	 * 1.构造抓取url
	 * 格式:search?channel=search&sw=javascript&ecode=utf-8&sectyear=2014&Pages=3
	 * 其中:
	 * channel=search	可以省略
	 * &sw=javascript	不可省略,要查询的关键字
	 * &ecode=utf-8		查询的编码,可以省略
	 * &sectyear=2014	查询的特定年份的图书
	 * &Pages=3			查询特定分页的图书
	 * 省略后的格式:search?sw=javascript
	 * 
	 * 获取当前查询条件下，一共获得多少本图书，可以分为多少页，保存下来，为下面分页抓取数据作准备
	 */
	public void getHowManyBooks(String wd,String year,String fenleiID,int currentPage,int dataNumber) throws Exception{
		String urlEnd =  "/search?channel=search&gtag=&sw=" + wd + "&ecode=utf-8&Field=all&Sort=&adminid=&btype=&seb=0&pid=0&year=&sectyear=" + year + "&showc=0&fenleiID=" + fenleiID + "&searchtype=&authid=0&exp=0&expertsw=&Pages=" + currentPage;
		String url = urlHead + urlEnd;
	        //打印相关信息 ：
	        System.out.println("访问的url是:" + url);
	        System.out.println("抓取的列别是:" + fenleiID);
	        System.out.println("抓取的关键字是:" + wd);
	        System.out.println("抓取的年份是:" + year);
	        System.out.println("抓取数据的页数:" + currentPage);
	        System.out.println("抓取数据的条数:" + dataNumber);
	        //一共多少本书
	    	int allNumbers = 0;
	    	//一共有多少页
	    	int allPages = 1;
	    	//存储当前页所有图书的url
		BufferedReader reader = new BufferedReader(new InputStreamReader((Connection.getConnection(url).getInputStream()), "utf-8"));
		//读取源代码，获得相关信息，拼凑下载url
		String line = null;
		while ((line = reader.readLine()) != null) {
			//当前查询条件下，一共获得多少本图书
			//<b>找到与 <font color=red> javascript</font> 相关的<font color=red>中文图书</font> 4533 种,用时 0.002 秒</b> 当前为第 1 页
			if(line.trim().toString().startsWith("<b>找到")){								
				Pattern pattern = Pattern.compile("</font> (\\d{1,20}) 种");
				Matcher matcher = pattern.matcher(line.trim());				
				if(matcher.find()){									
					// 图书前言页数
					allNumbers = Integer.parseInt(matcher.group(1));					
					System.out.println("一共找到的书籍个数:" + allNumbers);
					allPages = (allNumbers-1)/10+1;
					System.out.println("一共找到的书籍页数:" + allPages);
				}
				break;
			}
		}
		reader.close();
		//逐页抓取数据
		getBooks(wd,year,allNumbers,allPages,currentPage,dataNumber,fenleiID);
	}
	
	
	
public void getBooks(String wd,String year,int allNumbers,int allPages,int currentPage,int dataNumber,String fenleiID) throws Exception{
	
		//String urlEnd = "/search?channel=search&ecode=utf-8&sw=" + wd + "&sectyear=" + year + "&Pages=" + currentPage;
		//http://book.szdnet.org.cn/search?channel=search&gtag=&sw=javascript&ecode=utf-8&Field=all&Sort=&adminid=&btype=&seb=0&pid=0&year=&sectyear=&showc=0&fenleiID=&searchtype=1&authid=0&Pages=2
		String urlEnd =  "/search?channel=search&gtag=&sw=" + wd + "&ecode=utf-8&Field=all&Sort=&adminid=&btype=&seb=0&pid=0&year=&sectyear=" + year + "&showc=0&fenleiID=" + fenleiID + "&searchtype=&authid=0&exp=0&expertsw=&Pages=" + currentPage;
		String url = urlHead + urlEnd;
		Thread.currentThread().sleep(time);
		System.out.println("1" + url);
		BufferedReader reader = new BufferedReader(new InputStreamReader((Connection.getConnection(url).getInputStream()), "utf-8"));
		//读取源代码，获得相关信息，拼凑下载url
		String line = null;
		//抓取的图书个数计数，每页抓10个，抓够就break,不用再分析源码，节省实践
		int count = 1;
		//标示是否下一行数据是要抓取的数据
		boolean flag = false;
		books = new ArrayList();
				
		while ((line = reader.readLine()) != null) {	
			
			if(flag){
				//<A class=px14 href="/views/specific/2929/bookDetail.jsp?dxNumber=000010880535&d=57DE845D70ACAF6B33A867338E9AB403&fenlei=1817040302"
				Pattern pattern = Pattern.compile("href=\"(.*)\"");
				Matcher matcher = pattern.matcher(line.trim());				
				if(matcher.find()){									
					// 图书前言页数
					//2929这段数字抓取不到，这里手动替换
					books.add(urlHead + matcher.group(1));
				}
					
				flag = false;
				if(count++==10){
					break;
				}
			}
			if(line.trim().toString().equals("<td height=\"20\">")){
				//如果当前数据条数，不是目标开始条数，则下次判断
				if(count<dataNumber){
					count++;
					continue;
				}
				//下一行数据是要抓取的数据
				flag = true;
			}
			
		}
		reader.close();
		System.out.println("################################################################################################");
		System.out.println("开始抓取第 " + currentPage + " 页的数据");
		System.out.println("################################################################################################");
		saveToDatabase(books,currentPage,dataNumber,allNumbers,allPages);
		
		//不是最后一页，重复抓取
		if(++currentPage<=allPages){
			getBooks(wd,year,allNumbers,allPages,currentPage,1,fenleiID);
		}
	}

	/**
	 * 
	 * 分析页面,是否又传递，如果又，记录地址，存入数据库
	 * 
	 * 
	 */
	public void saveToDatabase(ArrayList books,int currentPage,int dataNumber,int allNumbers,int allPages) throws Exception{
		
		for(int i=0;i<books.size();i++){
			//图书试读的url地址后缀
			String readUrlAddress = "";
			//图书传递的url地址后缀
			String mailUrlAddress = "";
			//图书名称
			String bookName = "";
			//作者
			String author = "";
			//出版社
			String press = "";
			//图书出版年份,未知年份定义为1949年
			String publishYear = "1949";
			//图书出版月份,为止月份定义为00
			String publishMonth = "00";
			//图书页数
			String pages = "";
			//图书isbn编号
			String isbn = "";
			//图书价格
			String price = "0";
			//图书描述
			String bookDesc = "";
			//dxnumber
			String dxNumber = "";
			//fenlei
			String fenlei = "";
			//中图法图书分类
			String  stringCategoryId = "";
			
			System.out.println("\n抓取第" + currentPage + "/" + allPages + "页, " + "当前页的第" + (dataNumber)  + "条数据," + "所有页的第" +((currentPage-1)*10+dataNumber)+ "/" + allNumbers + "条数据");
			dataNumber++;

			
	        Thread.currentThread().sleep(time);
	        System.out.println(books.get(i).toString());
	        BufferedReader reader = new BufferedReader(new InputStreamReader((Connection.getConnection(books.get(i).toString()).getInputStream()), "utf-8"));
			String line = null;
			boolean isContentLine = false;
			while ((line = reader.readLine()) != null) {	
				
				// 内容提要:<br />
				//本书详细介绍Web编程基础中的相关知识和技能，主要内容包括HTML基础、表格表单和框架、CSS、页面布局、JavaScript基础、JavaScript对象、DOM编程、表单验证和特效、AJAX以及JQuery等等。全书对相关的理论知识进行了分析，文字通俗易懂，实例丰富，可以使读者更深入地理解相关……
				if(isContentLine){
					bookDesc = line.trim();
					System.out.println("图书描述:" + bookDesc);
					isContentLine = false;
				}
				
				 
				if(line.trim().startsWith("var dx=")){
					//获取dx
					//var dx="000015022085";
					Pattern pattern = Pattern.compile("var dx=\"(.*?)\"");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){									
						dxNumber = matcher.group(1).trim();
						System.out.println("dxnumber:" + dxNumber);
					}
				}else if(line.trim().startsWith("var feilei=")){
					//获取fenlei
					//var feilei="1817040302";
					Pattern pattern = Pattern.compile("var feilei=\"(.*?)\"");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){				
						fenlei = matcher.group(1).trim();
						System.out.println("fenlei:" + fenlei);
					}
				}else if(line.trim().startsWith("var bookname=")){
					//获取图书名称
					//var bookname="JavaScript网页特效案例教程";
					Pattern pattern = Pattern.compile("var bookname=\"(.*?)\"");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){									
						//图书名字处理，比如：把多个空格处理为1个空格
						bookName = matcher.group(1).trim();
						bookName = bookName.replaceAll("\\s+", " ");
						System.out.println("图书名称:" + bookName);
					}
				}else if(line.trim().startsWith("var allinfo=")){
					//作者，出版社，日期,isbn编号
					//出版社有三种格式情况
					//var allinfo="杨旭超主编||北京：机械工业出版社||2014.03||978-7-111-45596-7";
					//var allinfo="魏宇皓著||和硕科技文化有限公司||1996.05||957-8682-52-2";
					//var allinfo="（美）Tim Ritchey著；雷凌翼，孙学东译||北京：清华大学出版社；西蒙与舒斯特国际出版公司||1996.11||7-302-02402-2";
					
					//年,月有三种格式情况,年,月份可能不存在
					//var allinfo="江福松等编著；李权燕改编||北京：科学出版社；龙门书局||1996||7-03-005443-1";
					//var allinfo="魏宇皓著||和硕科技文化有限公司||1996.05||957-8682-52-2";
					//var allinfo="SBELLEY POWERS||南京：东南大学出版社||||978-7-5641-0779-6";
					
					//isbn编号有二种格式情况,可能不存在
					//var allinfo="（美）斯特凡洛夫著；李强译||北京：中国电力出版社||2014.02||";
					//var allinfo="魏宇皓著||和硕科技文化有限公司||1996.05||957-8682-52-2";
					
					Pattern pattern = Pattern.compile("var allinfo=\"(.*?)\\|\\|(.*?：)?(.*?)(；.*?)?\\|\\|(\\d{4})?((\\.)(\\d{2}))?\\|\\|(.*?)?\"");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){	
						author = matcher.group(1);
						press = matcher.group(3);
						publishYear = matcher.group(5);
						publishMonth = matcher.group(8);
						isbn =  matcher.group(9);
						if(author!=null)author = author.trim();
						if(press!=null)press = press.trim();
						if(publishYear!=null)publishYear = publishYear.trim();
						if(publishMonth!=null)publishMonth = publishMonth.trim();
						if(isbn!=null)isbn = isbn.trim();
						System.out.println("作者:" + author);
						System.out.println("出版社:" + press);
						System.out.println("出版年份:" + publishYear);
						System.out.println("出版月份:" + publishMonth);
						System.out.println("isbn:" + isbn);
					}
					
				}else if(line.trim().startsWith("<a onclick=\"statjz();\"")){
					// <a onclick="statjz();" href="/readDetail.jsp?dxNumber=000011724284&d=9D872AEAF890C7586AA1D4F7DE6ACDD0" target="_blank"><img src="images/readtest1.gif" width="61" height="19" /></a>
					//图书试读的url
					Pattern pattern = Pattern.compile("href=\"(.*?)\"");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){									
						//图书试读的url后缀
						readUrlAddress = matcher.group(1).trim();
					}
				}else if(line.trim().startsWith("内容提要:")){
					//标示下一行是内容简介
					isContentLine = true;					
				}else if(line.trim().startsWith("send_requestajax")){
					//send_requestajax("/views/specific/2929/getlibinfo.jsp?dx=000011724284&d=AB7E609FC7E9FEB5CEA29B3000CAE884&ssn=13303772&sname=Java%E7%A8%8B%E5%BA%8F%E8%AE%BE%E8%AE%A1%E6%95%99%E7%A8%8B&ebookurl=0&classid=1817040302&pkey=8081390","libinfo");
					//图书传递的url
					Pattern pattern = Pattern.compile("send_requestajax\\(\"(.*?)\"");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){									
						mailUrlAddress = new ChuanDi().getUrl(urlHead + matcher.group(1).trim());
					}
				}
				else if(line.trim().startsWith("【形态项】")){
					//【形态项】208
					//图书页数
					Pattern pattern = Pattern.compile(".*?(\\d{1,6})");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){					
						pages = matcher.group(1).trim();
						System.out.println("图书页数:" + matcher.group(1));
					}else{
						pages = "0";
						System.out.println("图书页数:" + pages);
					}
				}else if(line.trim().startsWith("【原书定价】")){
					//【原书定价】38.00
					//图书定价
					Pattern pattern = Pattern.compile("(\\d{1,5}(\\.)?(\\d{1,5})?)");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){						
						price = matcher.group(1).trim();
						System.out.println("图书定价:" + price);
					}else{
						price = "00.00";
						System.out.println("图书定价:" + price);
					}
					break;
				}
				else if(line.trim().startsWith("【中图法分类号】")){
					//【中图法分类号】TP312"/>
					Pattern pattern = Pattern.compile("【中图法分类号】(.*?)\"/>");
					Matcher matcher = pattern.matcher(line.trim());				
					if(matcher.find()){						
						stringCategoryId = matcher.group(1).trim();
						System.out.println("中图法分类号: " + stringCategoryId);
					}else{
						System.out.println("中图法分类号: " + stringCategoryId);
					}
					break;
				}
					
			}
			
			//保存图书,下载封面,创建目录结构
			if(readUrlAddress!=null && mailUrlAddress!=null){
				//////////图书可以传递,保存到数据库
				Book book = new Book();
				book.setFenlei(fenlei);
				book.setDxnumber(dxNumber);
				book.setAuthor(author);
				book.setPages(Integer.parseInt(pages));
				book.setPress(press);
				book.setBookName(bookName);
				if(publishYear==null){
					book.setPublishYear(1949);
				}else{
					book.setPublishYear(Integer.parseInt(publishYear));
				}
				if(publishMonth == null){
					book.setPublishMoth("00");
				}else{
					book.setPublishMoth(publishMonth);
				}
				book.setIsbn(isbn);
				book.setPrice(Float.parseFloat(price));
				book.setBookDesc(bookDesc);
				BookDao bookDao = new BookDao();
				//保存到数据库前，先判断该分类是否存在，如果不存在，则对分类进行降级
				CategoryDao c = new CategoryDao();
				while(fenlei.length()>0){
					if(null!=c.getCategoryByNumberCategoryId(fenlei)){
						break;
					}else{
						fenlei = fenlei.substring(0,fenlei.length()-2);
					}
				}
				book.setNumberCategoryId(fenlei);
				//////////把抓取的图书的基本信息存储到数据库
				if(bookDao.getBookByDxnumber(book.getDxnumber()) == null){
					System.out.println("数据保存结果: " + new BookDao().addBook(book));
				}else{
					System.out.println("数据在数据库中已经存在，不保存数据");
				}
				//////////下载图书封面，创建目录结构
				Download.download(urlHead + readUrlAddress, dir + createDir(fenlei) + "y" + book.getPublishYear() + "/" + "m" + book.getPublishMoth() + "/" + book.getDxnumber());
			}else{
				System.out.println("图书传递地址为空，不保存");
			}
			reader.close();
			
			
			//抓取一页数据，10条，间歇30秒
			if((dataNumber-1)==10){
				System.out.println("当前页抓取完毕，休眠30秒");
				Thread.sleep(30000);
			}else if(((currentPage-1)*10+dataNumber-1)==allNumbers){
				System.out.println("当前条件下所有数据抓取完毕，休眠30秒");
				Thread.sleep(30000);
			}
			
		}
	}
	
	//创建图书存储目录结构
	public String createDir(String fenlei){
		String dir = "";
		for(int i=2;i<=fenlei.length();){
			dir  += fenlei.substring(0, i) + "/";
			i+=2;
		}
		return dir;
	}
}
