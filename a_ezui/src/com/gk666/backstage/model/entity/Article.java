package com.gk666.backstage.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 文章表
 * @author zq
 *
 */
@Component
@Entity
@Table(name = "g_article")
public class Article extends BaseEntity{
	
	@Column(name = "g_content")
	private String content;
	
	@Column(name = "g_tittle")
	private String tittle;
	
	@Column(name = "g_views")
	private int views = 0;
	
	@Column(name = "g_point")
	private int point = 0;
   
	@Column(name = "g_describe")
	private String describe;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getViews() {
		return views;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}
}
