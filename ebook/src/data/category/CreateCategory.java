package data.category;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.CategoryDao;
import vo.Category;

/**
 * 抓取图书分类
 * 
 * 递归迭代查询，数据库中的分类
 * 
 * 中图分类法参考网站: 
 * http://www.ztflh.com/ 
 * http://ztflh.xhma.com/
 * 
 * 
 * 代码执行流程:
 * 1.建立数据库
 * 2.执行该程序，会根据上述网站，把分类抓取，然后存储到数据库
 * category_table.sql是表结构
 * category_data.sql是表机构和数据,注意文件比较大，不要用eclipse打开
 * 
 * 
 * 抓取后，需要对数据进行处理:
 * 1.CategoryName为空的数据,这些为空的分类都不重要
 * 2.stringCategoryId字段中带有/的数据，根据http://mpa.zjnu.net.cn/show.aspx?id=594&cid=8页面上的内容进行补充
 * 其中3/7,分别是 
 * 亚洲
    非洲
    欧洲
    大洋州
    美洲
    F753/757         |             4 | 06100502         | 061005                 | 各国对外贸易 
    insert into category values(null,"F753",4,"06100502","061005","亚洲对外贸易");
    insert into category values(null,"F754",4,"06100503","061005","非洲对外贸易");
    insert into category values(null,"F755",4,"06100504","061005","欧洲对外贸易");
    insert into category values(null,"F756",4,"06100505","061005","大洋洲对外贸易");
    insert into category values(null,"F757",4,"06100506","061005","美洲对外贸易");
    
     F733/737         |             4 | 06100303         | 061003                 | 各国               
     insert into category values(null,"F733",4,"06100303","061003","亚洲");
     insert into category values(null,"F734",4,"06100304","061003","非洲");
     insert into category values(null,"F735",4,"06100305","061003","欧洲");
     insert into category values(null,"F736",4,"06100306","061003","大洋洲");
     insert into category values(null,"F737",4,"06100307","061003","美洲");
    3.stringCategoryId字段中带有-的数据
    4.stringCategoryId字段中带有+的数据
    
 * @author zuoyang
 *
 */
public class CreateCategory {

	public static void main(String[] args) throws Exception {

		// 这里抓取的url是:
		// http://ztflh.xhma.com
		// http://ztflh.xhma.com/html/1.html -----
		// http://ztflh.xhma.com/html/45792.html
		// 数据抓取过程中有的名字为空串，不过很少，不影响,都是些比较偏门的分类
		new CreateCategory().getFenLei("http://ztflh.xhma.com", 1, "");

	}

	public void getFenLei(String url, int dataTypeLevel, String parentTypeId) throws Exception {

		// 保存子类url,下次抓取
		Map<String, String> sonUrls = new HashMap<String, String>();

		// 打印相关信息 ：
		// System.out.println("访问的url是: " + url);
		// 存储当前页所有图书的url
		URLConnection conn = new URL(url).openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		// 读取源代码，获得相关信息，拼凑下载url
		String line = null;

		// 当前分类下的排列序号
		int i = 1;
		String _i = "";
		while ((line = reader.readLine()) != null) {

			Category category = new Category();

			if (line.trim().toString().startsWith("<li><span>")) {
				// System.out.println(line.trim());

				if (i < 10) {
					_i = "0" + (i++);
				} else {
					_i = "" + (i++);
				}

				// 带子分类,带url
				// <li><span>A</span><a href="http://ztflh.xhma.com/html/2.html"
				// title="马克思主义、列宁主义、毛泽东思想、邓小平理论中图分类号">马克思主义、列宁主义、毛泽东思想、邓小平理论</a></li>
				boolean flag = false;
				Pattern pattern = Pattern
						.compile("<li><span>(.*?)</span><a href=\"(.*?)\" title=\".*?\">(.*?)</a></li>");
				Matcher matcher = pattern.matcher(line.trim());
				while (matcher.find()) {
					flag = true;
					// 记录子分类链接，下次分析
					sonUrls.put(matcher.group(2), (parentTypeId + _i));

					// 保存数据
					System.out.println("字符分类号:"
							+ matcher.group(1).replace("[", "").replace("]", "").replace("{", "").replace("}", "")
							+ ", 数字分类号:" + (parentTypeId + _i) + ",分类等级:" + dataTypeLevel + ", 父类数字分类号:" + parentTypeId
							+ ", 分类名称:" + matcher.group(3) + ",	url:" + matcher.group(2));

					category.setStringCategoryId(
							matcher.group(1).replace("[", "").replace("]", "").replace("{", "").replace("}", ""));
					category.setNumberCategoryId(parentTypeId + _i);
					category.setCategoryLevel(dataTypeLevel);
					category.setParentNumberCategoryId(parentTypeId);
					category.setCategoryName(matcher.group(3));
					if (new CategoryDao().getCategoryByStringCategoryId(category.getStringCategoryId()) == null) {
						if (new CategoryDao().addCategory(category)) {
							System.out.println("数据保存成功");
						} else {
							System.out.println("数据保存失败*************************");
						}
					} else {
						System.out.println("数据已经存在，不再保存");
					}

				}

				if (flag) {
					continue;
				}
				// 没有子分类,没有url
				// <li><span>Z13/17</span>各国丛书</li>
				pattern = Pattern.compile("<li><span>(.*?)</span>(.*?)</li>");
				matcher = pattern.matcher(line.trim());
				while (matcher.find()) {
					System.out
							.println("字符分类号:"
									+ matcher.group(1).replace("[", "").replace("]", "").replace("{", "").replace("}",
											"")
									+ ", 数字分类号:" + (parentTypeId + _i) + ",分类等级:" + dataTypeLevel + ", 父类数字分类号:"
									+ parentTypeId + ", 分类名称:" + matcher.group(2) + ",	url:null");
					category.setStringCategoryId(
							matcher.group(1).replace("[", "").replace("]", "").replace("{", "").replace("}", ""));
					category.setNumberCategoryId(parentTypeId + _i);
					category.setCategoryLevel(dataTypeLevel);
					category.setParentNumberCategoryId(parentTypeId);
					category.setCategoryName(matcher.group(2));
					if (new CategoryDao().getCategoryByStringCategoryId(category.getStringCategoryId()) == null) {
						if (new CategoryDao().addCategory(category)) {
							System.out.println("数据保存成功");
						} else {
							System.out.println("数据保存失败*************************");
						}
					} else {
						System.out.println("数据已经存在，不再保存");
					}
				}
			}
		}
		reader.close();

		// 1.存储当前数据

		++dataTypeLevel;
		// 2.把保存的url,再次递归，调用
		Iterator<Map.Entry<String, String>> entries = sonUrls.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();

			System.out.println("存储数据:" + entry.getKey() + "," + entry.getValue());
			getFenLei(entry.getKey(), dataTypeLevel, entry.getValue());
		}

	}

}
