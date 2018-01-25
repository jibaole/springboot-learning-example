package org.spring.springboot.bean;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;


/**
 * @description：solr 测试 描述: 映射的实体类必须有@ID主键
 * @author：JBL
 * @date：2017/12/17 17:46
 */
@SolrDocument(solrCoreName="cores")
public class SolrQuestionResult implements Serializable{

	private static final long serialVersionUID = -6031066219848718178L;
	@Id
	@Field
	private String id;
	@Field("question")
	private String question;
	@Field("knowledgeId")
	private String knowledgeId;
	@Field("knowName")
	private String knowName;
	@Field("answer")
	private String answer;
	@Field("msgType")
	private String msgType;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getKnowName() {
		return knowName;
	}
	public void setKnowName(String knowName) {
		this.knowName = knowName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "SolrQuestionResult [id=" + id + ", question=" + question + ", knowledgeId="
				+ knowledgeId + ", knowName=" + knowName + ", answer=" + answer + ", msgType=" + msgType + "]";
	}
}
