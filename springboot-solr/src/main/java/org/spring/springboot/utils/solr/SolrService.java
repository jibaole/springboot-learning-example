package org.spring.springboot.utils.solr;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.utils.PageInfoFacet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class SolrService {
    private static final Logger logger = LoggerFactory.getLogger(SolrService.class);

    @Autowired
    private SolrClient client;

    /**
     * 添加数据
     *
     * @param bean  对象
     * @throws IOException
     * @throws SolrServerException
     */
    public void add( Object bean) throws IOException, SolrServerException {
        logger.info("solr-add defaultCollection:{},bean:{}", bean.getClass().getName());
        client.addBean(bean);
        client.commit();
    }

    /**
     * 添加一组数据
     *
     * @param beans             list集合数据添加
     * @throws IOException
     * @throws SolrServerException
     */
    public void adds( Collection<?> beans) throws IOException, SolrServerException {
        logger.info("solr-adds defaultCollection:{},beans：{}", beans.getClass().getName());
        client.addBeans(beans);
        client.commit();
    }

    /**
     * 根据多个id删除数据
     * @param ids
     * @throws IOException
     * @throws SolrServerException
     */
    public void deleteByIds(List<String> ids) throws IOException, SolrServerException {
        logger.info("solr-delete defaultCollection:{},ids:{}", JSON.toJSONString(ids));
        client.deleteById(ids);
        client.commit();
    }

    /**
     * 根据ID删除数据
     * @param id                要删除的文档的id
     * @throws IOException
     * @throws SolrServerException
     */
    public void deleteById( String id) throws IOException, SolrServerException {
        logger.info("solr-delete-id defaultCollection:{},ids:{}", id);
        client.deleteById(id);
        client.commit();
    }


    /**
     * 根据指定索引(字段)模糊删除数据
     * @param field
     * @param fieldValue
     * @throws IOException
     * @throws SolrServerException
     */
    public void deleteByField(String field, String fieldValue) throws IOException, SolrServerException {
        logger.info("solr-delete-Field-id defaultCollection:{},field:{},fieldValue:{}", field, fieldValue);
        client.deleteByQuery(field + ":" + fieldValue);
        client.commit();
    }

    /**
     * 查询数据
     *
     * @param clazz             对象Po
     * @param query             查询条件
     * @param <T>               返回查询集合
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public <T> List<T> query( Class<T> clazz, SolrQuery query) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},field:{},query{}", clazz.getClass().getName(), query.getQuery());
        QueryResponse response = client.query(query);
        return response.getBeans(clazz);
    }

    /**
     * 查询
     *
     * @param query             查询条件
     * @return 返回response对象
     * @throws IOException
     * @throws SolrServerException
     */
    public QueryResponse query(SolrQuery query) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},query:{}", query.getQuery());
        QueryResponse response = client.query(query);
        return response;
    }

    /**

     * @param clazz             查询的数据对应的对象
     * @param query             查询条件
     * @param rowBounds         分页参数
     * @param <T>
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public <T> PageInfo query(Class<T> clazz, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {

        logger.info("solr-query-rowBounds defaultCollection:{},field:{},query:{}",  clazz.getClass().getName(), query.getQuery());

        if (rowBounds != null) {
            query.setStart(rowBounds.getOffset());
            query.setRows(rowBounds.getLimit());
        } else {
            Integer start = query.getStart();
            if (start == null) {
                start = 0;
            }
            Integer rows = query.getRows();
            Assert.notNull(start, "请设置分页条数rows");
            rowBounds = new RowBounds(start, rows);
        }
        QueryResponse response = client.query(query);
        SolrDocumentList documentList = response.getResults();
        List content = new DocumentObjectBinder().getBeans(clazz, documentList);
        Page page = new Page();
        page.addAll(content);
        page.setPageNum(rowBounds.getOffset());
        page.setPageSize(rowBounds.getLimit());
        page.setTotal(documentList.getNumFound());
        PageInfo pageInfo = new PageInfo(page);
        return pageInfo;
    }

    /**
     * 查询数据
     * @param query             查询条件
     * @param rowBounds         分页
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public PageInfo query(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},query:{}",  query.getQuery());

        if (rowBounds != null) {
            query.setStart(rowBounds.getOffset());
            query.setRows(rowBounds.getLimit());
        } else {
            Integer start = query.getStart();
            if (start == null) {
                start = 1;
            }
            Integer rows = query.getRows();
            Assert.notNull(start, "请设置分页条数rows");
            rowBounds = new RowBounds(start, rows);
        }
        QueryResponse response = client.query(query);
        SolrDocumentList documentList = response.getResults();
        List list = (List) documentList;


        Page page = new Page(rowBounds.getOffset(), rowBounds.getLimit());
        page.setTotal(documentList.getNumFound());
        page.addAll(list);


        PageInfo pageInfo = new PageInfo(page);

        return pageInfo;
    }

    /**
     * solrj的facet结果集查询
     *
     * @param query             查询条件
     * @param rowBounds         分页数
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public PageInfoFacet queryFacet(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
        logger.info("solr-query defaultCollection:{},query:{}");

        if (rowBounds != null) {
            query.setStart(rowBounds.getOffset() - 1);
            query.setRows(rowBounds.getLimit());
        } else {
            Integer start = query.getStart();
            if (start == null) {
                start = 1;
            }
            Integer rows = query.getRows();
            Assert.notNull(start, "请设置分页条数rows");
            rowBounds = new RowBounds(start, rows);
        }
        QueryResponse response = client.query(query);
        SolrDocumentList documentList = response.getResults();
        List list = (List) documentList;
        Page page = new Page(rowBounds.getOffset(), rowBounds.getLimit());
        page.setTotal(documentList.getNumFound());
        page.addAll(list);
        PageInfo pageInfo = new PageInfo(page);
        PageInfoFacet pageInfoFacet = new PageInfoFacet();
        pageInfoFacet.setPageInfo(pageInfo);
        pageInfoFacet.setFacetFieldList(response.getFacetFields());
        return pageInfoFacet;
    }

}

