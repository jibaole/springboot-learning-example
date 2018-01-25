package org.spring.springboot.bean.vo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * @description：
 * @author：JBL
 * @date：2018/1/25
 */
@SolrDocument(solrCoreName="cores")
public class GoodsInfoResult extends SolrBaseResult implements Serializable {
    private static final long serialVersionUID = -6031066219848718179L;
    @Id
    @Field
    private String id;
    @Field("goodsNo")
    private String goodsNo;
    @Field("goodsName")
    private String goodsName;
    @Field("goodsSubtitle")
    private String goodsSubtitle;
    @Field("goodsAdded")
    private String goodsAdded;
    @Field("goodsAddedTime")
    private String goodsAddedTime;
    @Field("goodsPreferPrice")
    private String goodsPreferPrice;
    @Field("goodsWeight")
    private String goodsWeight;
    @Field("goodsImgId")
    private String goodsImgId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSubtitle() {
        return goodsSubtitle;
    }

    public void setGoodsSubtitle(String goodsSubtitle) {
        this.goodsSubtitle = goodsSubtitle;
    }

    public String getGoodsAdded() {
        return goodsAdded;
    }

    public void setGoodsAdded(String goodsAdded) {
        this.goodsAdded = goodsAdded;
    }

    public String getGoodsAddedTime() {
        return goodsAddedTime;
    }

    public void setGoodsAddedTime(String goodsAddedTime) {
        this.goodsAddedTime = goodsAddedTime;
    }

    public String getGoodsPreferPrice() {
        return goodsPreferPrice;
    }

    public void setGoodsPreferPrice(String goodsPreferPrice) {
        this.goodsPreferPrice = goodsPreferPrice;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsImgId() {
        return goodsImgId;
    }

    public void setGoodsImgId(String goodsImgId) {
        this.goodsImgId = goodsImgId;
    }

    @Override
    public String toString() {
        return "GoodsInfoResult{" +
                "id='" + id + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSubtitle='" + goodsSubtitle + '\'' +
                ", goodsAdded='" + goodsAdded + '\'' +
                ", goodsAddedTime='" + goodsAddedTime + '\'' +
                ", goodsPreferPrice='" + goodsPreferPrice + '\'' +
                ", goodsWeight='" + goodsWeight + '\'' +
                ", goodsImgId='" + goodsImgId + '\'' +
                '}';
    }
}
