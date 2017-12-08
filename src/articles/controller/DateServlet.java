package articles.controller;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class DateServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
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
		req.setAttribute("url", "date/"+title_url);
		
		String[] split_date = title_url.split("_");
		
		Date date1 = new Date();
		Date date2 = new Date();
		if(split_date.length == 2)
		{
			@SuppressWarnings("deprecation")
			Date date3 = new Date(split_date[0]+"/30/"+split_date[1]);
			@SuppressWarnings("deprecation")
			Date date4 = new Date(split_date[0]+"/01/"+split_date[1]);
			date1 = date3;
			date2 = date4;
		} else {
			resp.sendRedirect("/");
		}
	
		//get list articles 
		
		Query query_article = psm.newQuery(Articles.class);
		query_article.setFilter("date < date1 && date > date2");
		query_article.declareParameters("java.util.Date date1,java.util.Date date2");
		query_article.setOrdering("date desc");
		query_article.setRange((limit*(re_page-1)), (limit*(re_page-1)+limit));
		@SuppressWarnings({ "unchecked" })
		List<Articles> listArticles = (List<Articles>) query_article.execute(date1,date2);
		
		req.setAttribute("listArticles", listArticles);
		
		//seo
		Format formatter;
		formatter = new SimpleDateFormat("MMM yyyy");
		
		req.setAttribute("seo_title", "Artículos : "+formatter.format(date2));
		req.setAttribute("seo_description","Lista de artículos " +formatter.format(date2)+ ".Libre reimpresión de artículos. Libre artículos para su sitio web y boletines informativos. Envíe sus artículos a nuestro Directorio de artículos.");
		req.setAttribute("title", "Artículos : "+formatter.format(date2) + " Page - "+re_page);
		req.setAttribute("description","Lista de artículos " +formatter.format(date2)+ ".Artículos gratis proporcionado por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
		req.setAttribute("keyword", "yooarticles.com,presente artículo libre, en el artículo, los artículos,"+formatter.format(date2));
		try {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
