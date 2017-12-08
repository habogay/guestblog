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
public class AuthorServlet extends HttpServlet {
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
		req.setAttribute("url", "author/"+title_url);
		
		//get list articles
		Query query_article = psm.newQuery(Articles.class);
		query_article.setFilter("authorAlias=='"+title_url+"'");
		query_article.setOrdering("date desc");
		query_article.setRange((limit*(re_page-1)), (limit*(re_page-1)+limit));
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute();
		
		req.setAttribute("listArticles", listArticles);
		
		//seo
		req.setAttribute("seo_title","Lista de artículos con el autor '"+title_url.replaceAll("[-]", " ")+"'");
		req.setAttribute("seo_description", "Lista de artículos con el autor "+title_url.replaceAll("[-]", " "));
		req.setAttribute("title", "Page "+re_page+"Autor : "+title_url.replaceAll("[-]", " "));
		req.setAttribute("description", "page "+re_page+" - author "+title_url.replaceAll("[-]+", " ")+".Free articles provided by yooarticles.com - your free articles directory. Find free online articles for your website, eZine or newsletters. Submit your Articles for free syndication and publication.");
		req.setAttribute("keyword", "yooarticles.com,presente artículo libre, en el artículo, los artículos,"+title_url.replaceAll("[-]", " "));
		
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
