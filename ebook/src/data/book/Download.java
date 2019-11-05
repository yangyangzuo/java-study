package data.book;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import book.download.Connection;

/**
 * 该类主要用来再抓取图书的时候，顺便把封面给下载了,并建立好目录结构
 * @author zuoyang
 *
 */
public class Download{
	//下载延迟时间
	public static int time = 5000;
	
	
	public static void main(String []args) throws Exception{
		//download("http://book.ucdrs.superlib.net/readDetail.jsp?dxNumber=000015463689&d=32177A6AFC2A699BF0EDF393D64D1DFC", "/home/zuoyang/Desktop/test/");
	}

	//根据试读链接获取实际地址
	//http://book.ucdrs.superlib.net/readDetail.jsp?dxNumber=000000445336&d=DC4BC687D1DAC02BA3E79CF263A374E6
	//>>>>>>>>>>>>>>>>>>
	//http://img.duxiu.com/n/jpgfs/book/base/10827368/d442c31757f643628e638c158eb95827/1f388400171df608cefe5af59918882c.shtml?uf=1&t=4&time=2016040715&url=http%3A%2F%2Fbook.ucdrs.superlib.net%2FbookDetail.jsp%3FdxNumber%3D000000445336%26d%3DDC4BC687D1DAC02BA3E79CF263A374E6%26rtype%3D1
	public static String getRealUrl(String url) throws Exception{
		HttpURLConnection connection = Connection.getConnection(url);
	        connection.setInstanceFollowRedirects(false);
	        String location = connection.getHeaderField("Location");
	        return location;
	}
	
	//再抓取页面的时候使用，只负责抓取封面和创建目录
	public static void download(String url,String saveDisk) throws Exception {
		// 图书出版年份
		int publicYear = 0;
		// 图书url,根据试读链接获取实际地址
		String targetUrl = getRealUrl(url);
		// 图书名字
		String bookName = "noName";
		// 图书前言页数
		int fowPages = 1;
		// 图书目录页数
		int bookDirPages = 1;
		// 图书正文内容开始页
		int contentStart = 1;
		// 图书正文内容结束页
		int contentEnd = 1;
		// 图书下载的真实路径 = _http + bookUrl + contentName + bookUrlEnd
		// 图书下载路径http头路径
		String _http = targetUrl.substring(0, targetUrl.indexOf("/n/"));
		//dxnumber
		String dxNumber = "dxnumber";
		
		// 图书所在的url路径
		String bookUrl = "";
		// 图书页码名称,根据页面的所在的页数构造一个6位的数字例如：第一页:000001,第11页：000011
		String contentName = "";
		// 图书下载路径http结尾,zoom=2表示下载的是最大的图片
		String bookUrlEnd = "?zoom=2";

		
		// 分析源代码读取url获得jpgpath
		// 还可以获得页面的相关信息,比如有多少页,封面多少页，目录多少页等等
		HttpURLConnection conn = Connection.getConnection(targetUrl);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "utf-8"));
		//读取源代码，获得相关信息，拼凑下载url
		String line = null;
		while ((line = reader.readLine()) != null) {			
			if (line.trim().startsWith("<title>")){
				//图书名称
				bookName = line.trim().replaceAll("<.+?>", "");		
				//图书名字处理，防止和系统冲突，特殊字符，不能作为文件或者目录的名称
				bookName = bookName.replace("\\", "").replace("/", "").replace(":", "").replace("*", "").replace("?", "").replace("\"", "").replace("<", "").replace(">", "").replace("|", "");
			}else if(line.trim().startsWith("<div id=\"bookinfo\"")){
				//出版年份
				//<div id="bookinfo" style="display:none">（加）约瑟夫（Joseph K·）著；陈毅东译,深入理解FreeBSD设备驱动程序开发,机械工业出版社,2013.02,</div>
				//<div id="bookinfo" style="display:none">（美）Murray Stokely,Nik Clayton著,FreeBSD使用手册,人民邮电出版社,2002年09月,</div>
				//<div id="bookinfo" style="display:none">（美）厄本（Urban,M.）  （美）泰曼（Tiemann,B.）著  智慧东方工作室译,FreeBSD技术内幕,机械工业出版社,2002年06月第1版,</div>
				String s = line.trim().replace(" ", "").replace(" ", "");				
				Pattern pattern = Pattern.compile(",(\\d{4})");
				Matcher matcher = pattern.matcher(s);				
				if(matcher.find()){									
					publicYear = Integer.parseInt(matcher.group(1));
				}				
			}else if(line.trim().startsWith("<input type=\"hidden\" id=\"url\"")){
				//试读页面,dxNumber
				// <input type="hidden" id="url" name="f[0].url" value="http://book.szdnet.org.cn/bookDetail.jsp?dxNumber=000012443008&d=0E24547CC59E528B8E4BA7E3C290E9F8&rtype=1" />
				Pattern pattern = Pattern.compile("value=\".*?dxNumber=(\\d{2,20})&.*?\"");
				Matcher matcher = pattern.matcher(line.trim());				
				if(matcher.find()){									
					dxNumber = matcher.group(1);
				}		
			}else if (line.trim().startsWith("var pages")) {
				// var pages = [[1,0],[书名1页],[版权1页],[前言], [目录页],[试读开始页，试读结束页],[1,0],[2,2]];
				// var pages = [[1,0],[1,1],[1,1],[1,12], [1,10], [1, 30], [1, 0], [2, 2]];	
				String s = line.trim().replace(" ", "").replace(" ", "");				
				Pattern pattern = Pattern.compile("\\[\\[\\d{1,4},\\d{1,4}\\],\\[\\d{1,4},\\d{1,4}\\],\\[\\d{1,4},\\d{1,4}\\],\\[\\d{1,4},(\\d{1,4})\\],\\[\\d{1,4},(\\d{1,4})\\],\\[(\\d{1,4}),(\\d{1,4})\\]");
				Matcher matcher = pattern.matcher(s);				
				if(matcher.find()){									
					// 图书前言页数
					fowPages = Integer.parseInt(matcher.group(1));
					// 图书目录页数
					bookDirPages = Integer.parseInt(matcher.group(2));
					// 图书正文内容开始页
					contentStart = Integer.parseInt(matcher.group(3));
					// 图书正文内容结束页
					contentEnd = Integer.parseInt(matcher.group(4));					
				}
			}else if (line.trim().startsWith("jpgPath")){
				//bookUrl
				bookUrl = line.trim().replace("jpgPath", "").replace(":","").replace("\"", "").replace(",","");				
			}else if(line.trim().startsWith("url:\"")){
				//传递页面,dxid
				//url:"/n/origin.jsp?dxid=000006948324&SSID=12608403&PageNo="+page+"&A=2D483E86A84D4BD83D3AF5776011C7C8&firstdrs="+escape('')+"&uid=",
				Pattern pattern = Pattern.compile("dxid=(\\d{2,20})&");
				Matcher matcher = pattern.matcher(line.trim());				
				if(matcher.find()){									
					dxNumber = matcher.group(1);
				}		
				break;
			}
		}
		reader.close();



		
		// ////////////文件夹初始化
		// 图书所在硬盘的文件夹名称
		//图书存储目录结构:	年份/dxnumber
		//例如:2013_javascript编程宝典_007645676567
		//存储:2013/007645676567
		
		
		// 打印图书相关信息:
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("图书名称:" + bookName);
		System.out.println("图书dxNumber:" + dxNumber);
		System.out.println("出版年份:" + publicYear);
		System.out.println("图书存储位置:" + saveDisk);
		System.out.println("图书前言页数:" + fowPages);
		System.out.println("图书目录页数:" + bookDirPages);
		System.out.println("正文内容页数：" + contentStart + "-" + contentEnd);

		////////////////////////////////////////////////////////////////////////////////////////////////////
		//构造大图，仅仅传zoom=2不行，还的构造一次pi=2
		//至于连接，任何一个图片请求都可以，这里使用的是cov001
		//1.构造url获得connection
		HttpURLConnection __connection = Connection.getConnection(_http + bookUrl + "cov001" + bookUrlEnd + "&pi=2");  
	        __connection.setInstanceFollowRedirects(false);
	        __connection.connect(); //重新链接，吧参数穿过去
	        //2.得到真实的图片地址
	        String __location = __connection.getHeaderField("Location");//得到真实的图片地址
	        __connection = Connection.getConnection(__location);  
	        __connection.connect(); //重新链接，吧参数穿过去
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
		// 1.创建图书目录
		File _bookDir = new File(saveDisk);
		if (!_bookDir.exists())
			_bookDir.mkdirs();

		// 2.图书封皮固定两个:cov001, cov002
		for (int i = 1; i < 3; i++) {
			File realFile = new File(saveDisk + "/cov00" + i + ".jpg");

			if (realFile.exists())
				continue;
			System.out.println("正在下载:" + bookName + "---cov00" + i + ".jpg");
			
			try{
				//3.构造url获得connection
				HttpURLConnection connection = Connection.getConnection(_http + bookUrl + "cov00"+ i + bookUrlEnd);  
				connection.setInstanceFollowRedirects(false);
				connection.connect(); //重新链接，吧参数穿过去
				//4.得到真实的图片地址
				String location = connection.getHeaderField("Location");//得到真实的图片地址
				connection =Connection.getConnection(location);  
				connection.connect(); //重新链接，吧参数穿过去
				BufferedImage bi = ImageIO.read(connection.getInputStream());
				
				Thread.sleep(time);
				// 保存到本地
				if (bi != null) {
					ImageIO.write(bi, "jpg", realFile);
					if(i==1){
						//添加水印
						//Img image = new Img();
						//image.fontWaterMark("http://www.yayabooks.cn", saveDisk + bookDir + "/cov00" + i + ".jpg", "宋体", Font.BOLD, Color.RED, 65,-80, 780,-10);
					}
				} else
					System.out.println("下载失败");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("下载失败");
			}
		}
		System.out.println("+---------------------------------------------------------------------------------------------+");

	}
}
