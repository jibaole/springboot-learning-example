package org.spring.springboot.bean.vo;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @description：
 * @author：JBL
 * @date：2018/1/25
 */

public class SolrBaseResult implements Serializable{
    /**业务类型*/
    @Field("type")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
