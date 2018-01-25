package org.spring.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.bean.FastKnowledgeCard;
import org.spring.springboot.utils.MyMapper;

import java.util.List;

/**
 * @description：快知识-内容Mapper
 * @author：JBL
 * @date：2017/12/12
 */

public interface FastKnowledgeCardMapper extends MyMapper<FastKnowledgeCard> {

    /**
     * 根据counmId,获取知识列表
     * @param counmId
     * @param fristPage
     * @return
     */
    List<FastKnowledgeCard> getList(@Param("counmId") String counmId, @Param("fristPage") int fristPage);

    /**
     * 点赞 +1
     * @param id
     * @return
     */
    int doPraise(@Param("id") int id);


    /**
     * 评论 +1
     * @param id
     * @return
     */
    int doComment(@Param("id") int id);
}
