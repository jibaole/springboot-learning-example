
# SpringBoot 集成Solr、SolrCloud

## 一、Solr 
> 安装(略)

> 启动Solr:tomcat启动

> UI界面查询:<http://127.0.0.1:8080/solr/index.html#/cores>

## 二、单机、集群
>单机:配置使用host
>集群:配置启用zk-host

## 三、测试

### 添加索引
> http://127.0.0.1:8081/addIndex(批量添加)
> http://127.0.0.1:8081/addGoodsIndex/N(添加或更新单条记录，N为id)

### 删除索引
>http://127.0.0.1:8081/deleteAllByIds(批量删除索引)
>http://127.0.0.1:8081/deleteAllById/N(删除单条索引)

## 四、表结构
```SQL
CREATE TABLE `goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_no` varchar(60) DEFAULT NULL COMMENT '货号',
  `goods_name` varchar(60) DEFAULT NULL COMMENT '货品名称',
  `goods_subtitle` varchar(256) DEFAULT NULL COMMENT '货品副标题',
  `goods_added` enum('0','3','2','1') DEFAULT NULL COMMENT '上架  0:下架   1:上架   2:未采集   3:线下',
  `goods_added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上架时间',
  `goods_prefer_price` decimal(20,2) DEFAULT NULL COMMENT '销售价格',
  `goods_weight` decimal(20,2) DEFAULT NULL COMMENT '重量',
  `goods_img_id` varchar(128) NOT NULL DEFAULT '' COMMENT '货品图片',
  PRIMARY KEY (`id`,`goods_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5578 DEFAULT CHARSET=utf8 COMMENT='货品信息表';

```