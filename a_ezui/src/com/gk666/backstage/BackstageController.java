package com.gk666.backstage;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gk666.backstage.dao.BaseDao;
import com.gk666.backstage.model.entity.Article;
import com.gk666.backstage.service.impl.ArticleService;
import com.gk666.util.JsonUtil;
import com.gk666.util.WriteUtil;

@Controller
@RequestMapping("/backstage")
public class BackstageController {
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private Article article;
	
	/**
	 * 后台首页
	 */
	@RequestMapping("index")
	public void index(){
	}
	
	@RequestMapping("getArticle")
	public String getArticle(){
		return "backstage/articles";
	}
	
	@RequestMapping("addArticle")
	public String addArticle(){
		return "backstage/addArticle";
	}
	
	@ResponseBody
	@RequestMapping("findArticles")
	public String findArticles(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows,String searchKey ){
		int intPage = (page == null || page == 0) ? 1 : page;// 分页
		int maxResults = (rows == null || rows == 0) ? 10 : rows;// 每页显示条数
		int firstResult = (intPage - 1) * maxResults;
		List<Article> articleList = articleService.articleList(firstResult,maxResults);
		int count = articleService.getCountArticle();
		return JsonUtil.getJsonFormatDateFromObject(articleList, count);
		
		/*WriteUtil writeUtil = new WriteUtil(request, response); 
		//writeUtil.writeEasyUIData(articleList, 20); 		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JsonUtil.getJsonFormatDateFromObject(articleList, 20));
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pw.close();
		}*/
	}
	@RequestMapping("saveArticle")
    public void saveArticle(HttpServletRequest request){
		articleService.saveArticle(request);
	}
	
	@RequestMapping("delArticle")
	public void delArticle(HttpServletRequest request,HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		for(String i : id){
			articleService.delArticle(i);
		}
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write("ok");
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pw.close();
		}

	}
	
	@RequestMapping("articleInfo")
	public void articleInfo(Model model,HttpServletRequest request){
		Article article = articleService.findArticleById(request);
		model.addAttribute("article", article);
	}
	
	@RequestMapping("editArticle")
	public void editArticle(HttpServletRequest request){
		articleService.editArticle(request);
	}
}
