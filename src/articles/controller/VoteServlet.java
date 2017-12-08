package articles.controller;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;

@SuppressWarnings("serial")
public class VoteServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String vote = req.getParameter("vote");
		String alias = req.getParameter("alias");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		Query query_article = psm.newQuery(Articles.class);
		query_article.setFilter("alias=='"+alias+"'");
		@SuppressWarnings("unchecked")
		List<Articles> article = (List<Articles>) query_article.execute();
		if(article.size()>0)
		{
			Articles rearticle = new Articles();
			
			if(vote.equals("yes"))
			{
				rearticle.setVodeYes((article.get(0).getVodeYes()+1));
				rearticle.setVodeNo(article.get(0).getVodeNo());
			} else {
				rearticle.setVodeYes(article.get(0).getVodeYes());
				rearticle.setVodeNo((article.get(0).getVodeNo()+1));
			}
			
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
			rearticle.setView(article.get(0).getView());

			psm.makePersistent(rearticle);
		}
	}

}
