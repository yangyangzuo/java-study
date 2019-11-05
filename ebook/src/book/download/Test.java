package book.download;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[]args){
		String s = "";
		//出版年份
		s = "社,2013.02,</div>";
//		s = "版社,2002年09月,</div>";
//		s = "出版社,2002年06月第1版,</div>";
		//不带月份的
//		s = "语出版社,1994年第1版,</div>";						
		Pattern pattern = Pattern.compile("(,(\\d{4}).*?(\\d{2}))|,(\\d{4})");
		Matcher matcher = pattern.matcher(s);
		
		if(matcher.find()){
			if(matcher.group(4)!=null){
				System.out.println("年:" + Integer.parseInt(matcher.group(4)));
				System.out.println("月:00");
			}else{
				System.out.println("年:" + Integer.parseInt(matcher.group(2)));
				System.out.println("月:" + matcher.group(3));
			}
		}		
	}
}
