package com.gk666.backstage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gk666.backstage.dao.ArticleDao;
import com.gk666.backstage.dao.BaseDao;
import com.gk666.backstage.model.entity.Article;

@Service("articleService")
public class ArticleService {
	
	@Resource
	private ArticleDao articleDao;
	@Resource
	private Article article;
	
	public List<Article> articleList(int firstResult,int maxResults) {
		List<Article> articleList = articleDao.getArticle(firstResult,maxResults);
	    return articleList;
	}
	public String saveArticle(HttpServletRequest request){
		String tittle = request.getParameter("tittle");
		String content = request.getParameter("content");
		String describe = request.getParameter("describe");
		String createUser = request.getParameter("createUser");

		article.setId(UUID.randomUUID().toString().replace("-", ""));
		article.setContent(content);
		article.setTittle(tittle);
		article.setDescribe(describe);
		article.setCreateUser("superGK");
		article.setViews(article.getViews());
		article.setPoint(article.getPoint());
		article.setCreateTime(new Date());
		article.setState(article.getState());
		articleDao.save(article);
		return "success";
	}
	public int getCountArticle(){
        return 	articleDao.getCountArticle();
	}
	public int delArticle(String id){
        return articleDao.delArticle(id);
	}
	public Article findArticleById(HttpServletRequest request){
		String id = request.getParameter("id");
        return articleDao.findArticleById(id);
	}
	public void editArticle(HttpServletRequest request){
		String tittle = request.getParameter("tittle");
		String content = request.getParameter("content");
		String describe = request.getParameter("describe");
		String id = request.getParameter("id");

		article.setId(id);
		article.setContent(content);
		article.setTittle(tittle);
		article.setDescribe(describe);
		article.setLastUpdateUser("superGK");
		article.setLastUpdateTime(new Date());
		
		articleDao.update(article);
	}
	
}
