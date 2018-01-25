
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