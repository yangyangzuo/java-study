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