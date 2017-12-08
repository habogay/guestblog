package articles.controller;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;

public class SitemapServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		Query query = psm.newQuery(Articles.class);
		query.setOrdering("date desc");
		query.setRange(0,500);
		@SuppressWarnings("unchecked")
		List<Articles> listArticles = (List<Articles>) query.execute();
		req.setAttribute("listArticles", listArticles);
		
		try {
			req.getRequestDispatcher("/sitemap.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
