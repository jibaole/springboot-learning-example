package org.spring.springboot.utils;

import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.response.FacetField;

import java.util.List;

/**
 * @description：
 * @author：JBL
 * @date：2017/12/17
 */

public class PageInfoFacet {
    private PageInfo pageInfo;

    private List<FacetField> facetFieldList;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<FacetField> getFacetFieldList() {
        return facetFieldList;
    }

    public void setFacetFieldList(List<FacetField> facetFieldList) {
        this.facetFieldList = facetFieldList;
    }
}
