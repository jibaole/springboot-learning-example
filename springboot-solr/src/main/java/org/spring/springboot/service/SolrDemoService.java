package org.spring.springboot.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.spring.springboot.bean.SolrQuestionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolrDemoService {
    @Autowired
    private SolrClient client;

    public SolrDocumentList query(String param) throws IOException, SolrServerException {
        try {
            //查询参数对象，继承了SolrParams抽象类
            ModifiableSolrParams params =new ModifiableSolrParams();
            //查询条件
            String par="question:"+param;
            params.add("q",par);
            System.out.println(par);
           // 这里的分页和mysql分页一样
            params.add("start","0");
            params.add("rows","20");
            QueryResponse query = client.query(params);
            // 查询结果
            SolrDocumentList results = query.getResults();
            // 将查询结果直接转化为List，这里有个坑，对象每个属性必须要加 @Field("id") 属性，包为import org.apache.solr.client.solrj.beans.Field;
            // 如果不加属性的话，会返回相等长度的的List，但是List里面每个对象的值均为空
            return results;
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    /**
     * 添加
     */
    public void add() {
        try {
            List<SolrQuestionResult> list = new ArrayList<SolrQuestionResult>();
            for(int i = 0 ; i < 500;i++){
                SolrQuestionResult item = new SolrQuestionResult();
                item.setId( i+"" );
                item.setKnowName( ("item_" + (i+1)) );
                item.setQuestion( "setQuestion" +i);
                item.setAnswer( "setAnswer" +i );
                item.setKnowledgeId( i +"");
                item.setMsgType( "setMsgType"+i );
                list.add(item);
            }
            client.addBeans(list);
            client.commit();
            System.out.println("添加完成");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

