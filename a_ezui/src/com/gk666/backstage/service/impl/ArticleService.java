package com.gk666.backstage.service.impl;

import java.util.List;

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
	
	public List<Article> articleList(int firstResult,int maxResults) {
		List<Article> articleList = articleDao.getArticle(firstResult,maxResults);
	    return articleList;
	}
	public String saveArticle(Article article){
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
		System.out.println(id+"+++++++++++++++++++++++++++++++");
        return articleDao.findArticleById(id);
	}
	
}
