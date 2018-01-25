package org.spring.springboot.service;

import org.spring.springboot.bean.GoodsInfo;
import org.spring.springboot.bean.vo.GoodsInfoResult;
import org.spring.springboot.mapper.GoodsInfoMapper;
import org.spring.springboot.utils.SystemConfig;
import org.spring.springboot.utils.solr.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description：商品信息
 * @author：JBL
 * @date：2018/1/25
 */
@Service
public class GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private SolrService solrService;

    public List<GoodsInfo> getAll() {
        return goodsInfoMapper.selectAll();
    }

    /**
     * 添加单个商品
     * @param id
     * @return
     */
    public String addGoodsIndex(int id){
        int nextIn= new Random().nextInt(100);
        GoodsInfoResult result = new GoodsInfoResult();
        result.setId(id+"");
        result.setGoodsNo( "2085060110425"+nextIn );
        result.setGoodsName( "荣耀 6 (H60-L01) 低配版 白色 移动4G"+nextIn);
        result.setGoodsSubtitle( "荣耀 6 (H60-L01) 低配版 白色 移动4G手机"+nextIn );
        result.setGoodsAdded("0" );
        result.setGoodsAddedTime( "2018-09-17 13:03:47" );
        result.setGoodsPreferPrice( "1499" );
        result.setGoodsWeight("999" );
        result.setGoodsImgId( "http://img01.ningpai.com/1433126974075.jpg" );
        result.setType( SystemConfig.GOODS_INFO);//商品类型
        try {
            solrService.add(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "添加索引失败!";
        }
        return "添加索引成功!";
    }

    /**
     * 添加所有商品索引
     *
     * @return
     */
    public String addIndex() {
        try {
            List<GoodsInfoResult> list = new ArrayList<GoodsInfoResult>();
            goodsInfoMapper.selectAll().forEach( goods -> {
                GoodsInfoResult result = new GoodsInfoResult();
                result.setId( goods.getId().toString() );
                result.setGoodsNo( goods.getGoodsNo() );
                result.setGoodsName( goods.getGoodsName() );
                result.setGoodsSubtitle( goods.getGoodsSubtitle() );
                result.setGoodsAdded( goods.getGoodsAdded() );
                result.setGoodsAddedTime( goods.getGoodsAddedTime() );
                result.setGoodsPreferPrice( goods.getGoodsPreferPrice() );
                result.setGoodsWeight( goods.getGoodsWeight() );
                result.setGoodsImgId( goods.getGoodsImgId() );
                result.setType( SystemConfig.GOODS_INFO );//商品类型
                list.add( result );
            } );
            solrService.adds( list );
        } catch (Exception e) {
            return "添加索引失败!";
        }
        return "添加索引成功!";
    }

    /**
     * 清空所有商品
     * @return
     */
    public String deleteAllByIds(){
        List<String> ids =new ArrayList<>(  );
        goodsInfoMapper.selectAll().forEach( goodsInfo -> {
            ids.add( goodsInfo.getId().toString());
        } );
        try {
            solrService.deleteByIds( ids );
        } catch (Exception e) {
            e.printStackTrace();
            return "商品索引，失败!";
        }
        return "商品索引，成功!";
    }

    /**
     * 删除单个商品
     * @param id
     * @return
     */
    public String deleteAllById(String id){
        try {
            solrService.deleteById( id );
        } catch (Exception e) {
            e.printStackTrace();
            return "删除"+id+"商品索引，失败!";
        }
        return"删除"+id+"商品索引，成功!";
    }
}
