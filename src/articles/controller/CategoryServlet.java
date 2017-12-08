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
import articles.model.Category;

@SuppressWarnings("serial")
public class CategoryServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		String path = ((HttpServletRequest)req).getRequestURI();
		
		StringTokenizer st = new StringTokenizer( path,"/");
		int count = st.countTokens(); 
        String page = "1";

        if(count != 2 && count != 3)
        {
        	resp.sendRedirect("/");
        }
        
		// skip one token /question/abcd (remove sites)
		st.nextToken();
		String title_url = st.nextToken();
		
		if(count == 3)
		{
			page = st.nextToken();
		}
		//phan trang
		int limit = 15; 
		
		for (int i = 0; i < page.length(); i++) {
			 if ((page.charAt(i) >= 'A' && page.charAt(i) <= 'Z') || (page.charAt(i) >= 'a' && page.charAt(i) <= 'z')) {
				 resp.sendRedirect("/");
	             break;
	         }
       }
		
		int re_page = Integer.parseInt(page);
		req.setAttribute("page", page);
		req.setAttribute("url", "category/"+title_url);
		
		//get list articles
		Query query_article = psm.newQuery(Articles.class);
		query_article.setFilter("categoryAlias=='"+title_url+"'");
		query_article.setOrdering("date desc");
		query_article.setRange((limit*(re_page-1)), (limit*(re_page-1)+limit));
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute();
		
		req.setAttribute("listArticles", listArticles);
		
		//get detailcategory
		Query query_category = psm.newQuery(Category.class);
		query_category.setFilter("alias=='"+title_url+"'");
		@SuppressWarnings("unchecked")
		List<Category> category = (List<Category>) query_category.execute();
		req.setAttribute("category", category);
		//seo
		if(category.size()>0)
		{
			req.setAttribute("seo_title",category.get(0).getName());
			req.setAttribute("seo_description", category.get(0).getDescription().getValue());
			req.setAttribute("title", "Page "+page+" - "+category.get(0).getName());
			req.setAttribute("description", "Page "+page+" - "+category.get(0).getDescription().getValue()+".Artículos gratis proporcionado por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
			req.setAttribute("keyword", category.get(0).getKeyword());
			try {
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resp.sendRedirect("/");
		}
		
		
	}

}
