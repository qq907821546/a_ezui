package com.gk666.backstage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gk666.backstage.model.entity.Article;

@Repository("articleDao")
public class ArticleDao extends BaseDao{
	
	public List<Article> getArticle(int firstResult,int maxResults){
		//String hql = "FROM Article";
		//return super.findByHql(hql);
		return super.findPageByEntityName("Article", firstResult, maxResults);
	}
	public int getCountArticle(){
        return 	super.getCountByHql("SELECT COUNT(*) FROM Article");	
	}
	public int delArticle(String id){
		return super.deleteByEntityNameAndId("Article", id);
	}
	public Article findArticleById(String id){
		return (Article) super.findById("Article", id);
	}
}
