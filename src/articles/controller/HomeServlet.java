package articles.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		String path = ((HttpServletRequest)req).getRequestURI();
		
		StringTokenizer st = new StringTokenizer( path,"/");
        int count = st.countTokens(); 
        String page = "1";
        
        if(count==2)
        {
        	st.nextToken();
    		page = st.nextToken();
        }

		// skip one token /question/abcd (remove sites)

		req.setAttribute("page", page);
		req.setAttribute("title_url", "");
		req.setAttribute("url", "home");

		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();		
		
		//phan trang
		int limit = 15;
		
		for (int i = 0; i < page.length(); i++) {
			 if ((page.charAt(i) >= 'A' && page.charAt(i) <= 'Z') || (page.charAt(i) >= 'a' && page.charAt(i) <= 'z')) {
				 resp.sendRedirect("/");
	             break;
	         }
       }
		
		int re_page = Integer.parseInt(page);
		
		//game
		Query query_article = psm.newQuery(Articles.class);
		query_article.setOrdering("date desc");
		query_article.setRange((limit*(re_page-1)), (limit*(re_page-1)+limit));
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute();
		
		req.setAttribute("listArticles", listArticles);
		
		//seo
		req.setAttribute("seo_title", "Lista de artículos");
		req.setAttribute("seo_description", "Si usted está interesado en una alternativa de alto valor a la compra de un coche nuevo, un auto usado puede ser la mejor opción para usted.Coches usados tienen una tasa estabilizada de depreciacción que.");
		req.setAttribute("title", "Page "+re_page+" - Lista de artículos");
		req.setAttribute("description", "Page - "+re_page+" - lista de artículos article.Free proporcionada por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
		req.setAttribute("keyword", "yooarticles.com,presente artículo libre, en el artículo, los artículos");
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
