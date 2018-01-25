package org.spring.springboot.bean;

import javax.persistence.Table;

/**
 * @description：
 * @author：JBL
 * @date：2018/1/25
 */
@Table(name = "goods_info")
public class GoodsInfo extends BaseEntity{

    private String goodsNo;
    private String goodsName;
    private String goodsSubtitle;
    private String goodsAdded;
    private String goodsAddedTime;
    private String goodsPreferPrice;
    private String goodsWeight;
    private String goodsImgId;

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
}
