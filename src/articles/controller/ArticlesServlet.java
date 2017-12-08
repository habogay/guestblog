package articles.controller;


import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;

@SuppressWarnings("serial")
public class ArticlesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
 
		//phan trang
		int limit = 15; 
		int re_page = Integer.parseInt("1");
		
		req.setAttribute("page", "1");
		req.setAttribute("url", "home");
		
		//game
		Query query_article = psm.newQuery(Articles.class);
		query_article.setOrdering("date desc");
		query_article.setRange((limit*(re_page-1)), (limit*(re_page-1)+limit));
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute();
		
		req.setAttribute("listArticles", listArticles);
		 
		//seo
		req.setAttribute("seo_title", "Últimos artículos");
		req.setAttribute("seo_description", "Diverso proporcionados por yooarticles.com - tu directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, ezine o boletín de noticias. Envíe sus artículos para la distribución gratuita y publicación.");
		req.setAttribute("title", "Enviar artículos libres - Enviar comunicado de prensa en línea de forma gratuita");
		req.setAttribute("description", "Diverso proporcionados por yooarticles.com - tu directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, ezine o boletín de noticias. Envíe sus artículos para la distribución gratuita y publicación.");
		req.setAttribute("keyword", "yooarticles.com,submit free article,article,articles");
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
