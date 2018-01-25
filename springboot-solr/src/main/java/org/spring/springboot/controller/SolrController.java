package org.spring.springboot.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.spring.springboot.bean.SolrQuestionResult;
import org.spring.springboot.service.SolrDemoService;
import org.spring.springboot.utils.solr.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description：
 * @author：JBL
 * @date：2017/12/17
 */
@Controller
@RequestMapping("/")
public class SolrController {
    @Autowired
    SolrDemoService demoService;
    @Autowired
    SolrService solrService;


    @RequestMapping("/search")
    @ResponseBody
    String search() {
        demoService.add();
        return "添加索引成功";
    }


    @RequestMapping("/query/{param}")
    @ResponseBody
    SolrDocumentList query(@PathVariable String param) {
        SolrDocumentList documentList = null;
        try {
            documentList = demoService.query( param );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return documentList;
    }


    /**
     * 增加索引
     */
    @RequestMapping("/add")
    String add() {
            SolrQuestionResult item = new SolrQuestionResult();
            item.setId("demo");
            item.setKnowName("O(∩_∩)O哈哈~");
            item.setQuestion( "O(∩_∩)O哈哈~");
            item.setAnswer( "O(∩_∩)O哈哈~");
            item.setKnowledgeId( "O(∩_∩)O哈哈~");
            item.setMsgType( "client"  );
        try {
            solrService.add( item );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 增加索引
     */
    @RequestMapping("/adds")
    String adds() {
        List<SolrQuestionResult> list = new ArrayList<SolrQuestionResult>();
        for (int i = 0; i < 500; i++) {
            SolrQuestionResult item = new SolrQuestionResult();
            item.setId( i + "_client" );
            item.setKnowName( ("client_" + (i + 1)) );
            item.setQuestion( "client" + i );
            item.setAnswer( "client" + i );
            item.setKnowledgeId( i + "" );
            item.setMsgType( "client" + i );
            list.add( item );
        }
        try {
            solrService.adds( list );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return "";
    }


    @RequestMapping("/deleteByIds")
    String deleteByIds() {
        List<String> list =new ArrayList<String>(  );
        for (int i = 0; i < 500; i++) {
            list.add(  i +"");
        }
        try {
            solrService.deleteByIds(list );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return "";
    }

}
