package book.download;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import data.book.TestLoadImage;


public class Connection {
	public static String cookie = null;
	//对连接添加cookie,和验证码校验
	public static HttpURLConnection getConnection(String url) throws Exception{
		if(Connection.cookie==null){
			getCookie();
		}
		System.out.println("2" + url);
		HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
		//模拟浏览器请求:
		conn.setRequestProperty("Accept" , "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.setRequestProperty("Accept-Encoding" , "identity");
		conn.setRequestProperty("Accept-Language" , "en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
		conn.setRequestProperty("Connection" , "keep-alive");
		conn.setRequestProperty("Cookie" , Connection.cookie);
		conn.setRequestProperty("Host" , "book.szdnet.org.cn");
		conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
		
		//验证码校验,如果进行了验证码请求，则重新构造最初的url请求
		if(veryfiCode(conn)){
			System.out.println("验证码处理完毕:" + url);
			 return getConnection(url);
		};
		return conn;
	}
	
//	public static void main(String[]args) throws MalformedURLException, IOException{
//		//获取cookie
//		String veryficodeurl = "http://img.duxiu.com/n/processVerifyPng.ac?t=" + new Random().nextFloat();
//		HttpURLConnection conn1 = (HttpURLConnection)new URL(veryficodeurl).openConnection();
//		String cookie = "";
//		String value,key;
//		for(int i = 1;(key =conn1.getHeaderFieldKey(i)) !=null;i++){
//			if(key.equalsIgnoreCase("set-cookie")){
//				value=conn1.getHeaderField(i);
//				value=value.substring(0,value.indexOf(";"));
//				cookie=cookie+value+";";
//			}
//		}
//		System.out.println(cookie);
//		//下载验证码
//		HttpURLConnection conn2 = (HttpURLConnection)new URL(veryficodeurl).openConnection();
//		conn2.setRequestProperty("Cookie" , cookie);
//		BufferedImage bi = ImageIO.read(conn2.getInputStream());
//		if (bi != null) {
//			ImageIO.write(bi, "png", new File(path));
//		}
//		//输入验证码确认
//		Thread t = Thread.currentThread();
//		TestLoadImage f = new TestLoadImage();
//		f.shows(path,f,t);
//		try{
//			Thread.currentThread().sleep(999999);
//		}catch(Exception e){
//		}
//		String veryfyCode = f.content;
//		//把验证码发送到服务端确认
//		String processVerifyurl = "http://img.duxiu.com/n/processVerify.ac?ucode=";
//		HttpURLConnection conn3 = (HttpURLConnection)new URL(processVerifyurl+veryfyCode).openConnection();
//		conn3.setRequestProperty("Cookie" , cookie);
//		System.out.println(processVerifyurl + veryfyCode);
//		BufferedReader reader = new BufferedReader(new InputStreamReader(conn3.getInputStream(), "utf-8"));
//		conn3.connect();
//	}
	//验证码临时存储位置
	private static String path = Connection.class.getClass().getResource("/").getPath() + "code.png";
	//对链接进行验证码处理,返回的结果标示是否进行了验证码处理
	public static boolean veryfiCode(HttpURLConnection conn) throws Exception{
		boolean result = false;
		String veryficodeurl = "http://img.duxiu.com/n/processVerifyPng.ac?t=" + new Random().nextFloat();
		String processVerifyurl = "http://img.duxiu.com/n/processVerify.ac?ucode=";
		String veryfyCode  = "";
		//获得http响应码,如果响应码是302,且以antispiderShowVerify.ac结尾
		conn.setInstanceFollowRedirects(false);
		if(conn.getResponseCode()==302&&conn.getHeaderField("Location").endsWith("antispiderShowVerify.ac")){
			System.out.print("需要验证码处理");
			//验证码是读秀页面的特定cookie
			String cookie = "";
			String value,key;
			for(int i = 1;(key =conn.getHeaderFieldKey(i)) !=null;i++){
				if(key.equalsIgnoreCase("set-cookie")){
					value=conn.getHeaderField(i);
					value=value.substring(0,value.indexOf(";"));
					cookie=cookie+value+";";
				}
			}
			System.out.println(cookie);
			//下载验证码
			HttpURLConnection conn1 = (HttpURLConnection)new URL(veryficodeurl).openConnection();
			conn1.setRequestProperty("Cookie" , cookie);
			BufferedImage bi = ImageIO.read(conn1.getInputStream());
			if (bi != null) {
				ImageIO.write(bi, "png", new File(path));
			}
			Thread t = Thread.currentThread();
			//输入验证码确认
			TestLoadImage f = new TestLoadImage();
			f.shows(path,f,t);
			try{
				Thread.currentThread().sleep(999999);
			}catch(Exception e){
			}
			veryfyCode = f.content;
			//把验证码发送到服务端确认
			HttpURLConnection conn2 = (HttpURLConnection)new URL(processVerifyurl+veryfyCode).openConnection();
			conn2.setRequestProperty("Cookie" , cookie);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "utf-8"));
			conn2.connect();
			result = true;
		}else{
			conn.setInstanceFollowRedirects(true);
		}
		return result;
	}
	
	
	/**
	 * http://www.ucdrs.superlib.net/网站抓取时候获得cookie
	 */
	public static String getCookie() throws Exception{
		if(Connection.cookie!=null){
			return cookie;
		}
		//获得cookie步骤
		//1.访问http://www.ucdrs.superlib.net/js/dxwritecookie.action?Field=&channel=&sw=&backurl=任意搜索关键字页面
		//例如:http://www.ucdrs.superlib.net/js/dxwritecookie.action?Field=&channel=&sw=&backurl=http%3A%2F%2Fbook.ucdrs.superlib.net%2Fsearch%3Fsw%3Da%26allsw%3D%26bCon%3D%26ecode%3Dutf-8%26channel%3Dsearch%26Field%3Dall
		//此时会得到如下cookie
		//Set-Cookie:userIPType_abo=1; Domain=ucdrs.superlib.net; Path=/
		//Set-Cookie:userName_dsr=""; Domain=ucdrs.superlib.net; Path=/
		//Set-Cookie:enc_abo=A8A4B0A033E2E8462153267F85A75FB0; Domain=ucdrs.superlib.net; Path=/
		//Set-Cookie:JSESSIONID=8A3145A267CA5A0545F3970E4DB34A78.kdqarea222; Path=/; HttpOnly
		//Set-Cookie:nopubuser_abo=1; Domain=ucdrs.superlib.net; Path=/
		//Set-Cookie:groupenctype_abo=1; Domain=ucdrs.superlib.net; Path=/
		//Set-Cookie:AID_dsr=""; Domain=ucdrs.superlib.net; Path=/
		//Set-Cookie:DSSTASH_LOG=C%5f35%2dUN%5f%2d1%2dUS%5f%2d1%2dT%5f1459993497026; Domain=.ucdrs.superlib.net; Path=/
		//Set-Cookie:groupId=431; Domain=ucdrs.superlib.net; Path=/
		//还会得到一个location
		//2.访问上一步骤种的location,并把上面的cookie传递过去
		//并获新的cookie
		//3.访问最终的抓取页面，并把步骤2种的cookie传递过去即可
		String cookie = "";
		String location = "";
		//步骤1:
		String url = "http://www.ucdrs.superlib.net/js/dxwritecookie.action?Field=&channel=&sw=&backurl=http%3A%2F%2Fbook.ucdrs.superlib.net%2Fsearch%3Fsw%3Da%26allsw%3D%26bCon%3D%26ecode%3Dutf-8%26channel%3Dsearch%26Field%3Dall";
		HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
		conn.setInstanceFollowRedirects(false);
		location = conn.getHeaderField("Location");
		String value,key;
		for(int i = 1;(key =conn.getHeaderFieldKey(i)) !=null;i++){
			if(key.equalsIgnoreCase("set-cookie")){
				value=conn.getHeaderField(i);
				value=value.substring(0,value.indexOf(";"));
				cookie=cookie+value+";";
			}
		}
		conn.disconnect();
		//System.out.println("步骤1完成");
		//步骤2:
		conn = (HttpURLConnection)new URL(location).openConnection();
		conn.setRequestProperty("Cookie" , cookie);
		conn.connect(); 
		conn.setInstanceFollowRedirects(false);
		for(int i = 1;(key =conn.getHeaderFieldKey(i)) !=null;i++){
			if(key.equalsIgnoreCase("set-cookie")){
				value=conn.getHeaderField(i);
				value=value.substring(0,value.indexOf(";"));
				cookie=cookie+value+";";
			}
		}
		conn.disconnect();
		//System.out.println("步骤2完成");
		Connection.cookie = cookie;
		return cookie;
	}
	
	
	/**
	 * 	http://search.szdnet.org.cn/网站抓取的时候cookie的获取方式
	 */
	public static String getCookie2() throws Exception{
		if(Connection.cookie!=null){
			return cookie;
		}
		//获得cookie步骤
		//1.访问http://search.szdnet.org.cn/js/dxwritecookie.action?Field=&channel=&sw=&backurl=任意搜索关键字页面
		//例如:http://search.szdnet.org.cn/js/dxwritecookie.action?Field=&channel=&sw=&backurl=http%3A%2F%2Fbook.szdnet.org.cn%2Fsearch%3FField%3Dall%26channel%3Dsearch%26sw%3Djava%26edtype%3D%26ecode%3Dutf-8
		//此时会得到如下cookie
		//Set-Cookie:JSESSIONID=D9329E314467A41E7C446CF83D8FC973.gharea43; Path=/; HttpOnly
		//Set-Cookie:DSSTASH_LOG=C%5f35%2dUN%5f%2d1%2dUS%5f%2d1%2dT%5f1439536890940; Domain=.szdnet.org.cn; Path=/
		//Set-Cookie:AID_dsr=""; Domain=szdnet.org.cn; Path=/
		//Set-Cookie:userIPType_abo=1; Domain=szdnet.org.cn; Path=/
		//Set-Cookie:userName_dsr=""; Domain=szdnet.org.cn; Path=/
		//Set-Cookie:enc_abo=A8A4B0A033E2E8462153267F85A75FB0; Domain=szdnet.org.cn; Path=/
		//Set-Cookie:groupId=12; Domain=szdnet.org.cn; Path=/
		//Set-Cookie:nopubuser_abo=1; Domain=szdnet.org.cn; Path=/
		//Set-Cookie:groupenctype_abo=1; Domain=szdnet.org.cn; Path=/
		//还会得到一个location
		//2.访问上一步骤种的location,并把上面的cookie传递过去
		//并获新的cookie
		String cookie = "";
		String location = "";
		//步骤1:
		String url = "http://search.szdnet.org.cn/js/dxwritecookie.action?Field=&channel=&sw=&backurl=http%3A%2F%2Fbook.szdnet.org.cn%2Fsearch%3FField%3Dall%26channel%3Dsearch%26sw%3Djava%26edtype%3D%26ecode%3Dutf-8";
		HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
		conn.setInstanceFollowRedirects(false);
		location = conn.getHeaderField("Location");
		String value,key;
		for(int i = 1;(key =conn.getHeaderFieldKey(i)) !=null;i++){
			if(key.equalsIgnoreCase("set-cookie")){
				value=conn.getHeaderField(i);
				value=value.substring(0,value.indexOf(";"));
				cookie=cookie+value+";";
			}
		}
		conn.disconnect();
		//System.out.println("步骤1完成");
		//步骤2:
		conn = (HttpURLConnection)new URL(location).openConnection();
		conn.setRequestProperty("Cookie" , cookie);
		conn.connect(); 
		conn.setInstanceFollowRedirects(false);
		location = conn.getHeaderField("Location");
		for(int i = 1;(key =conn.getHeaderFieldKey(i)) !=null;i++){
			if(key.equalsIgnoreCase("set-cookie")){
				value=conn.getHeaderField(i);
				value=value.substring(0,value.indexOf(";"));
				cookie=cookie+value+";";
			}
		}
		conn.disconnect();
		//System.out.println("步骤2完成");
		Connection.cookie = cookie;
		return cookie;
	}
	
}





