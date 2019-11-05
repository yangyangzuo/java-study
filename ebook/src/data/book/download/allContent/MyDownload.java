package data.book.download.allContent;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class MyDownload{
	public static void main(String[] args) {
		String[] urls = new String[] {
			//这个主要用来下载传递后的页面
"http://www.zhengzhifl.cn/n/drspath/book/base/14256989/14a8466d4e7e48b4b90c897c5c0beafe/2a4813283c0ab51ee0d8c686dea978d4.shtml?bt=2018-04-07&dm=-1805745327&et=2018-04-27&fid=45350840&username=yayabooks",
"http://www.maliemlun.cn/n/drspath/book/base/14256989/ac68e7e142ae402da390b983c91c889d/8b29b0f66e0bcb5bb7fc8ceba15795e4.shtml?bt=2018-04-07&dm=1251318180&et=2018-04-27&fid=45350832&username=yayabooks",

		};
		for (int i = 0, length = urls.length; i < length; i++)
			new Download(urls[i]).start();
	}
}

class Download extends Thread {
	//图书存储根路径
	private static String saveDisk = "/Users/gudandan/Desktop/图书/";
	//图书下载地址链接
	private String url;
	// 图书下载延迟时间,默认3000ms,即:3秒
	private static int time = 2000;
	
	public Download(String url) {
		this.url = url;
	}
	@Override
	public void run() {
		try {
			Download.getContent(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获取cookie
	public static String getCookie(URLConnection conn){
		String cookie="",key="",value="";
		for(int i = 1;(key =conn.getHeaderFieldKey(i)) !=null;i++){
			if(key.equalsIgnoreCase("set-cookie")){
				value=conn.getHeaderField(i);
				value=value.substring(0,value.indexOf(";"));
				cookie=cookie+value+";";
			}
		}
		return cookie;
	}
	
	public static void getContent(String url) throws IOException,InterruptedException {
		//cookie
		String cookie = "";
		// 图书出版年份
		int publicYear = 0;
		//图书出版月份
		String publicMoth = "00";
		// 图书url
		String targetUrl = url;
		// 图书名字
		String bookName = "noName";
		// 图书保存目录
		String bookDir = "noNameDir";
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
		// 分析源代码读取url获得jpgpath,还可以获得页面的相关信息,比如有多少页,封面多少页，目录多少页等等
		URLConnection conn = new URL(targetUrl.toString()).openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		cookie = getCookie(conn);
		
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
				//不带月份的
				//<div id="bookinfo" style="display:none">（美）R.A.柳文编著  徐森荣译,世界语-英语-汉语  微生物遗传学词典,中国世界语出版社,1994年第1版,</div>
				String s = line.trim().replace(" ", "").replace(" ", "");				
				Pattern pattern = Pattern.compile("(,(\\d{4}).*?(\\d{2}))|,(\\d{4})");
				Matcher matcher = pattern.matcher(s);				
				if(matcher.find()){				
					if(matcher.group(4)!=null){
						publicYear = Integer.parseInt(matcher.group(4));
						publicMoth = "00";
					}else{
						publicYear = Integer.parseInt(matcher.group(2));
						publicMoth = matcher.group(3);
					}
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
		//图书存储目录结构:	年份_图书名称_dxnumber
		// 例如：2013_javascript编程宝典_007645676567
		// 显示分类id,方便在网盘上保存图书目录,dxnumber为了防止图书名字相同
		
		
		bookDir = "y" + publicYear + "/" + "m" + publicMoth + "/" + publicYear + "_" + bookName + "_" + dxNumber;
		bookDir = publicYear + "_" + bookName + "_" + dxNumber;
		// 打印图书相关信息:
		System.out.println("+---------------------------------------------+");
		System.out.println("图书名称:" + bookName);
		System.out.println("图书dxNumber:" + dxNumber);
		System.out.println("出版年份:" + publicYear);
		System.out.println("出版月份:" + publicMoth);
		System.out.println("图书存储位置:" + saveDisk + bookDir);
		System.out.println("图书前言页数:" + fowPages);
		System.out.println("图书目录页数:" + bookDirPages);
		System.out.println("正文内容页数：" + contentStart + "-" + contentEnd);
		System.out.println("+---------------------------------------------+");

		////////////////////////////////////////////////////////////////////////////////////////////////////
		//构造大图，仅仅传zoom=2不行，还的构造一次pi=2
		//至于连接，任何一个图片请求都可以，这里使用的是cov001
		//1
		URL __url = new URL(_http + bookUrl + "cov001" + bookUrlEnd + "&pi=2");  
		HttpURLConnection __connection = (HttpURLConnection)__url.openConnection();  //构造url获得connection
	        //把cookie传递过去,这样可以得到location的值
	        __connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	        __connection.setInstanceFollowRedirects(false);
	        __connection.connect(); //重新链接，吧参数穿过去
	        //2
	        String __location = __connection.getHeaderField("Location");//得到真实的图片地址
	        __url = new URL(__location);  
	        __connection = (HttpURLConnection)__url.openConnection();  //构造url获得connection
	        //把cookie传递过去,这样可以得到location的值
	        __connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	        __connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");	            
	        __connection.connect(); //重新链接，吧参数穿过去
	        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
		// 1.创建图书目录
		File _bookDir = new File(saveDisk + bookDir);
		if (!_bookDir.exists())
			_bookDir.mkdirs();

		// 2.图书封皮固定两个:cov001, cov002
		for (int i = 1; i < 3; i++) {
			File realFile = new File(saveDisk + bookDir + "/cov00" + i + ".jpg");

			if (realFile.exists())
				continue;
			System.out.println("正在下载:" + bookName + "---cov00" + i + ".jpg");
			
			try{
				
				
	            
	            //3
	            URL _url = new URL(_http + bookUrl + "cov00"+ i + bookUrlEnd);  
				HttpURLConnection connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
	            connection.setInstanceFollowRedirects(false);
	            connection.connect(); //重新链接，吧参数穿过去
	            //4
	            String location = connection.getHeaderField("Location");//得到真实的图片地址
	            _url = new URL(location);  
	            connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");	            
	            connection.connect(); //重新链接，吧参数穿过去
				BufferedImage bi = ImageIO.read(connection.getInputStream());
				Thread.sleep(time);
				
				
				// 保存到本地
				if (bi != null) {
					ImageIO.write(bi, "jpg", realFile);
					
					if(i==1){
						//添加水印
//						Img image = new Img();
//						image.fontWaterMark("https://shop341393106.taobao.com", saveDisk + bookDir + "/cov00" + i + ".jpg", "宋体", Font.BOLD, Color.RED, 50,-80, 780,-10);
					}
				} else
					System.out.println("下载失败");
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("下载失败");
			}
		}

		// 书名页固定一个:bok001
		File realFile = new File(saveDisk + bookDir + "/bok001.jpg");
		if (!realFile.exists()) {
			try{
				
			System.out.println("正在下载:" + bookName + "---bok001.jpg");
            //3
            URL _url = new URL(_http + bookUrl + "bok001" + bookUrlEnd);  
			HttpURLConnection connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
            //把cookie传递过去,这样可以得到location的值
            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
            connection.setRequestProperty("Referer", targetUrl);
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
            connection.setInstanceFollowRedirects(false);
            connection.connect(); //重新链接，吧参数穿过去
            //4
            String location = connection.getHeaderField("Location");//得到真实的图片地址
            _url = new URL(location);  
            connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
            //把cookie传递过去,这样可以得到location的值
            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
            connection.setRequestProperty("Referer", targetUrl);
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");	            
            connection.connect(); //重新链接，吧参数穿过去
			BufferedImage bi = ImageIO.read(connection.getInputStream());
			Thread.sleep(time);
			
			// 保存到本地
			if (bi != null) {
				ImageIO.write(bi, "jpg", realFile);
			} else
				System.out.println("下载失败");
			}catch(Exception e){
				System.out.println("下载失败");
			}
		}

		// 版权页固定一个:leg001
		realFile = new File(saveDisk + bookDir + "/leg001.jpg");

		if (!realFile.exists()) {
			try{
				
				System.out.println("正在下载:" + bookName + "---leg001.jpg");
	            //3
	            URL _url = new URL(_http + bookUrl + "leg001"+ bookUrlEnd);  
				HttpURLConnection connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
	            connection.setInstanceFollowRedirects(false);
	            connection.connect(); //重新链接，吧参数穿过去
	            //4
	            String location = connection.getHeaderField("Location");//得到真实的图片地址
	            _url = new URL(location);  
	            connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");	            
	            connection.connect(); //重新链接，吧参数穿过去
				BufferedImage bi = ImageIO.read(connection.getInputStream());
				Thread.sleep(time);
				
			
			
			// 保存到本地
			if (bi != null) {
				ImageIO.write(bi, "jpg", realFile);
			} else
				System.out.println("下载失败");
			}catch(Exception e){
				System.out.println("下载失败");
			}
		}

		// 前言:fow001
		for (int i = 1; i <= fowPages; i++) {
			// 个位数
			if (i / 10 == 0)
				contentName = "fow00" + i;
			// 十位数
			else if (i / 100 == 0)
				contentName = "fow0" + i;
			// 白位数
			else if (i / 1000 == 0)
				contentName = "fow" + i;

			realFile = new File(saveDisk + bookDir + "/" + contentName + ".jpg");
			if (realFile.exists())
				continue;


			System.out.println("正在下载:" + bookName + "---" + contentName
					+ ".jpg");
			try{
				

				//3
	            URL _url = new URL(_http + bookUrl	+ contentName + bookUrlEnd);  
				HttpURLConnection connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
	            connection.setInstanceFollowRedirects(false);
	            connection.connect(); //重新链接，吧参数穿过去
	            //4
	            String location = connection.getHeaderField("Location");//得到真实的图片地址
	            _url = new URL(location);  
	            connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");	            
	            connection.connect(); //重新链接，吧参数穿过去
				BufferedImage bi = ImageIO.read(connection.getInputStream());
				Thread.sleep(time);
				
			// 保存到本地
			if (bi != null) {
				ImageIO.write(bi, "jpg", realFile);
			} else
				System.out.println("下载失败");
			}catch(Exception e){
				System.out.println("下载失败");
			}
		}

		// 目录:!00001
		for (int i = 1; i <= bookDirPages; i++) {
			// 个位数
			if (i / 10 == 0)
				contentName = "!0000" + i;
			// 十位数
			else if (i / 100 == 0)
				contentName = "!000" + i;
			// 白位数
			else if (i / 1000 == 0)
				contentName = "!00" + i;

			realFile = new File(saveDisk + bookDir + "/" + contentName + ".jpg");
			if (realFile.exists())
				continue;

			Thread.sleep(time);

			System.out.println("正在下载:" + bookName + "---" + contentName
					+ ".jpg");
			try{

				//3
	            URL _url = new URL(_http + bookUrl + contentName + bookUrlEnd);  
				HttpURLConnection connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
	            connection.setInstanceFollowRedirects(false);
	            connection.connect(); //重新链接，吧参数穿过去
	            //4
	            String location = connection.getHeaderField("Location");//得到真实的图片地址
	            _url = new URL(location);  
	            connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");	            
	            connection.connect(); //重新链接，吧参数穿过去
				BufferedImage bi = ImageIO.read(connection.getInputStream());
				Thread.sleep(time);

			// 保存到本地
			if (bi != null) {
				ImageIO.write(bi, "jpg", realFile);
			} else
				System.out.println("下载失败");
			}catch (Exception e) {
				System.out.println("下载失败");
			}
		}

		// 3.图书正文的url地址，然后读取写入到本地
		for (int i = contentStart; i <= contentEnd; i++) {
			// 个位数
			if (i / 10 == 0)
				contentName = "00000" + i;
			// 十位数
			else if (i / 100 == 0)
				contentName = "0000" + i;
			// 白位数
			else if (i / 1000 == 0)
				contentName = "000" + i;
			// 千位数
			else if (i / 10000 == 0)
				contentName = "00" + i;

			realFile = new File(saveDisk + bookDir + "/" + contentName + ".jpg");
			if (realFile.exists()) {
				if (i == contentEnd)
					System.out
							.println("///////////////////////" + bookName + ":"
									+ contentStart + "-" + contentEnd
									+ ":全部下载完成");
				continue;
			}

			System.out
					.println("正在下载:" + bookName + "--" + contentName + ".jpg");
			Thread.sleep(time);
			try{

				//3
	            URL _url = new URL(_http + bookUrl + contentName + bookUrlEnd);  
				HttpURLConnection connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
	            connection.setInstanceFollowRedirects(false);
	            connection.connect(); //重新链接，吧参数穿过去
	            //4
	            String location = connection.getHeaderField("Location");//得到真实的图片地址
	            _url = new URL(location);  
	            connection = (HttpURLConnection)_url.openConnection();  //构造url获得connection
	            //把cookie传递过去,这样可以得到location的值
	            connection.setRequestProperty("Cookie" , cookie);//设置connection参数
	            connection.setRequestProperty("Referer", targetUrl);
	            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");	            
	            connection.connect(); //重新链接，吧参数穿过去
				BufferedImage bi = ImageIO.read(connection.getInputStream());
				
				
				
				// 保存到本地
				if (bi != null) {
					ImageIO.write(bi, "jpg", realFile);
					if (i == contentEnd)
						System.out.println("///////////////////////" + bookName
								+ ":" + contentStart + "-" + contentEnd
								+ ":全部下载完成");
				}else{
					System.out.println("下在失败");
				}
			}catch (Exception e) {
				System.out.println("下载失败");
			}
		}

	}
}
