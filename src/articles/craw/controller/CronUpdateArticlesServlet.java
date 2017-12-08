package articles.craw.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;
import articles.string.TimeStam;

@SuppressWarnings("serial")
public class CronUpdateArticlesServlet extends HttpServlet {
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		Query query_article = psm.newQuery(Articles.class);
		query_article.setOrdering("vodeYes asc");
		query_article.setRange(0,7);
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute();
		if(listArticles.size() >0)
		{
			for(int i=0;i<listArticles.size();i++)
			{	
				listArticles.get(i).setVodeYes(TimeStam.time());
				
				psm=JDOHelper.getPersistenceManager(listArticles.get(i));
				psm.currentTransaction().begin();
				psm.makePersistent(listArticles.get(i));
				psm.currentTransaction().commit();
			}
		}
	}
}
