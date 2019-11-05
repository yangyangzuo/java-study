package book.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChuanDi {
	
		//根据图书的xhttprequest请求，获得图书传递地址
		public String getUrl( String xhr) throws Exception{
			String url = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader((Connection.getConnection(xhr).getInputStream()), "utf-8"));
			String line = null;
			//判断页面中是否又试读,如果又没有，说明无法传递
			while ((line = reader.readLine()) != null) {	
				
				
				//<h3 class="boxHd">文献传递：subtoRefer(true,'/gofirstdrs.jsp?dxNumber=000011724284&d=678C95575063FC0014B2A89BC635AED9')" >图书馆文献传递</A></h3>
//				System.out.println(line);
				Pattern pattern = Pattern.compile("subtoRefer\\(true,'(.*?)'\\)");
				Matcher matcher = pattern.matcher(line.trim());				
				if(matcher.find()){
					//图书试读的url后缀
					url = matcher.group(1);
					break;
				}
			}
			reader.close();
			return url;
		}
}
