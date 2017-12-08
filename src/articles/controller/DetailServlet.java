package articles.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheManager;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;

@SuppressWarnings("serial")
public class DetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().println("Esta página no existe o se ha eliminado, por favor, seleccione una página");
		
		resp.setHeader( "Cache-Control", "public, max-age=86400");
		resp.setHeader( "Expires", new Date(Calendar.getInstance().getTime().getTime()+24*60*60*1000).toGMTString());
		
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		String path = ((HttpServletRequest)req).getRequestURI();
		StringTokenizer st = new StringTokenizer( path,"/");
        int count = st.countTokens(); 

        if(count != 2)
        {
        	resp.sendRedirect("/");
        }
        
		// skip one token /question/abcd (remove sites)
		st.nextToken();
		String title_url = st.nextToken();
		
		//get detail articles
		Query query_article = psm.newQuery(Articles.class);
		query_article.setFilter("alias=='"+title_url+"'");
		@SuppressWarnings("unchecked")
		List<Articles> article = (List<Articles>) query_article.execute();
		req.setAttribute("article", article);
		
		if(article.size()>0)
		{
			Cache cache=null;

	        try {
	            cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
	        } catch (CacheException e) {
	           e.printStackTrace();
	           
	        }
	        String[] bisi = new String[2];	
	        bisi[0] = article.get(0).getTitle();
	        bisi[1] = article.get(0).getAlias();
	        if(cache.containsKey("lastPing"))
	        {
	        	@SuppressWarnings("unchecked")
				ArrayList<String[]> lastPing = (ArrayList<String[]>)cache.get("lastPing");
	        	if(lastPing.size()>6)
	        	{
	        		lastPing.remove(0);
	        	}
	        	int check = 0;
	        	for (int i = 0; i < lastPing.size(); i++) {
					if(lastPing.get(i)[1].equals(bisi[1]))
					{
						check = 1;
					}
				}
	        	if(check == 0)
	        		lastPing.add(bisi);
	        		cache.put("lastPing", lastPing);
	        	
	        }
	        else
	        {
	        	ArrayList<String[]> lastPing = new ArrayList<String[]>();
	        	lastPing.add(bisi);
        		cache.put("lastPing", lastPing);
	        }
			
			Query query_list = psm.newQuery(Articles.class);
			query_list.setOrdering("date desc");
			query_list.setFilter("authorAlias=='"+article.get(0).getAuthorAlias()+"'");
			query_list.setRange(0,5);
			@SuppressWarnings("unchecked")
			List<Articles> list_article = (List<Articles>) query_list.execute();
			req.setAttribute("list_article", list_article);
			String des = article.get(0).getContent().getValue();
			if(des.length() > 250)
			{
				des = article.get(0).getContent().getValue().substring(0, 250);
			}
			req.setAttribute("title", article.get(0).getTitle());
			req.setAttribute("description", des.replaceAll("\\<.*?\\>", "").replaceAll("[^.a-zA-Z0-9]+", " "));
			req.setAttribute("keyword", article.get(0).getKeyword().getValue());
			
			Articles rearticle = new Articles();
			
			rearticle.setAlias(article.get(0).getAlias());
			rearticle.setAuthor(article.get(0).getAuthor());
			rearticle.setAuthorAlias(article.get(0).getAuthorAlias());
			rearticle.setCategoryAlias(article.get(0).getCategoryAlias());
			rearticle.setContent(article.get(0).getContent());
			rearticle.setDate(article.get(0).getDate());
			rearticle.setEmail(article.get(0).getEmail());
			rearticle.setArticleId(article.get(0).getArticleId());
			rearticle.setKey(article.get(0).getKey());
			rearticle.setKeyword(article.get(0).getKeyword());
			rearticle.setLink(article.get(0).getLink());
			rearticle.setLinkEmbed(article.get(0).getLinkEmbed());
			rearticle.setTitle(article.get(0).getTitle());
			rearticle.setView(article.get(0).getView()+1);
			rearticle.setVodeNo(article.get(0).getVodeNo());
			rearticle.setVodeYes(article.get(0).getVodeYes());
			
			psm.makePersistent(rearticle);
			try {
				req.getRequestDispatcher("/detail.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resp.sendRedirect("/");
		}
		
	}

}
