package articles.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;
import articles.model.Keyword;

@SuppressWarnings("serial")
public class TagServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader( "Cache-Control", "public, max-age=86400");
		resp.setHeader( "Expires", new Date(Calendar.getInstance().getTime().getTime()+24*60*60*1000).toGMTString());
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
		req.setAttribute("url", "tags/"+title_url);
		
		//get list articles
		Query query_article = psm.newQuery(Articles.class);
		
		Query query_keyword = psm.newQuery(Keyword.class);
		query_keyword.setFilter("alias=='"+title_url+"'");
		@SuppressWarnings("unchecked")
		List<Keyword> keyword = (List<Keyword>) query_keyword.execute();
		String where = "";
		if(keyword.size()>0)
		{
			//tao chuoi dieu kien or
			
			if(keyword.size() > 0)
			{
				String list_articles = String.valueOf(keyword.get(0).getKeyGame().getValue());
				String[] article = list_articles.split(",");
				for(int i=(limit*(re_page-1));i<article.length;i++)
				{
					if(where.equals(""))
					{
						where = "articleId=='"+article[i]+"'";
					} else {
						where = where + "|| articleId=='"+article[i]+"'";
					}
					if(i==(limit*(re_page-1)+9))
					{
						break;
					}
				}
			}
			if(where.equals(""))
			{
				where = "articleId=='start'";
			}
			query_article.setFilter(where);
			query_article.setOrdering("date desc");
		}
		resp.getWriter().println(where);
//		query_article.setRange((limit*(re_page-1)), (limit*(re_page-1)+limit));
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute();
		
		req.setAttribute("listArticles", listArticles);

		//seo
		req.setAttribute("seo_title","Lista de artículos con etiquetas '"+title_url.replaceAll("[-]+", " ")+"'");
		req.setAttribute("seo_description", "Lista de artículos con etiquetas <strong>"+title_url.replaceAll("[-]+", " ")+"</strong>");
		req.setAttribute("title","Page " +re_page+ " - Tags : "+title_url.replaceAll("[-]+", " "));
		req.setAttribute("description", "page "+re_page+" - tags "+title_url.replaceAll("[-]+", " ")+". artículos gratis proporcionado por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
		req.setAttribute("keyword", title_url.replaceAll("[-]+", " ")+",yooarticles.com,presente artículo libre, en el artículo, los artículos");
		
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
