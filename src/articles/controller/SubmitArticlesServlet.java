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
import articles.model.Category;

@SuppressWarnings("serial")
public class SubmitArticlesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		//get list category
		Query query_category = psm.newQuery(Category.class);
		query_category.setOrdering("name ASC");
		@SuppressWarnings("unchecked")
		List<Category> list_category = (List<Category>) query_category.execute();
		req.setAttribute("list_category", list_category);
		
		//array check error
		String[] check = new String[11];
		String[] data = new String[9];
		
		req.setAttribute("check", check);
		req.setAttribute("data", data);
		
		req.setAttribute("title", "Enviar un artículo libre");
		req.setAttribute("description", "Enviar artículos libres article.Free proporcionada por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
		req.setAttribute("keyword", "yooarticles.com,presente artículo libre, en el artículo, los artículos");
		
		try {
			req.getRequestDispatcher("/submit.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
