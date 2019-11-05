drop database books;
create database books;
use books;


-- 图书分类
create table category(
	id int not null primary key auto_increment,
	-- 字符分类标识,中图法分类序号标示,例如:A,A1,A2
	stringCategoryId varchar(100) not null,
	-- 分类等级
	categoryLevel int,
	-- 数字分类标识,这个标示，主要时和读秀中的fenleiID做对应，例如:fenleiID=010804
	numberCategoryId varchar(100) not null,
	-- 上级数字分类标识,1级分类的上级数字分类为
	parentNumberCategoryId varchar(100),
	-- 分类名称,中图法分类名称,例如:交通运输,航空、航天
	CategoryName varchar(100) not null
);



create table book (
	id int not null primary key auto_increment,
	-- dxnumber,这里是000015127518
	-- dxNumber=000015127518&d=2CD23B1B2430AB2764C28557CC16427E&fenlei=1817040302
	-- 该值作为图书唯一标识,抓取的时候，判断该图书是否被抓取過	
    dxnumber varchar(100),
    
    -- 读秀fenlei号
	-- dxNumber=000015127518&d=2CD23B1B2430AB2764C28557CC16427E&fenlei=1817040302,这里是1817040302
    fenlei varchar(500),
    
	-- 这个字段和category中的numberCategoryId对应,当查询一个图书的分类的时候，根据这个字段查询该图书属于哪个分类
	-- 这个字段根据fenlei的值计算而来，如果fenlei值过长，会进行缩短降级，匹配category表中的数值
    numberCategoryId varchar(500),
   
	-- 书名: 给生意人看的人脉经营书
	bookName varchar(200),
	-- 作者: 仁久
	author varchar(200),
	-- 页数
	pages int,	
	-- 出版社: 中国华侨出版社
	press varchar(200),
	-- 出版时间: 2011年06月
	publishYear int,
	-- 出版月份:无数据，则为00
	publishMoth varchar(200),
	-- (ISBN编号)书号: 9787511314635
	isbn varchar(200),	
	-- 价格
	price float default 0,
	-- 图书简介
	bookDesc varchar(5000)
);


