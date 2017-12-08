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

@SuppressWarnings("serial")
public class RssServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		Query query_article = psm.newQuery(Articles.class);
		query_article.setOrdering("date desc");
		query_article.setRange(0, 30);
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute();
		
		req.setAttribute("listArticles", listArticles);
		
		req.setAttribute("title", "Enviar artículos gratis - Presentar el comunicado de prensa en línea de forma gratuita");
		req.setAttribute("description", "Artículos gratis proporcionado por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
		
		try {
			req.getRequestDispatcher("/rss.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
