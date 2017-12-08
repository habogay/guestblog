package articles.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import com.google.appengine.api.datastore.Text;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;
import articles.model.Category;
import articles.model.Keyword;
import articles.string.Replace;

@SuppressWarnings("serial")
public class PostArticlesServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		//array check error
		String[] check = new String[8];
		
		Articles insert = new Articles();
		insert.setVodeNo(0);
		insert.setVodeYes(0);
		insert.setView(0);
		insert.setAuthor(Replace.remove(req.getParameter("author")).replaceAll("\\<.*?\\>", ""));
		if(req.getParameter("author") != null && req.getParameter("author").equals(""))
		{
			check[0] = "Nombre completo del autor no está vacío";
		}
		insert.setCategoryAlias(req.getParameter("categoryAlias"));
		if(req.getParameter("categoryAlias") != null && req.getParameter("categoryAlias").equals(""))
		{
			check[1] = "Por favor, seleccione una categoría";
		}
		
		Text content = new Text(req.getParameter("content"));
		insert.setContent(content);
		if(req.getParameter("content").length() < 500)
		{
			check[2] = "El contenido debe más de 500 caracteres";
		}
		
		Date date = new Date();
		insert.setDate(date);
		insert.setEmail(req.getParameter("email"));
		String email = req.getParameter("email");

	    //Set the email pattern string
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");

	    //Match the given string with the pattern
	    Matcher m = p.matcher(email);

	    //check whether match is found 
	    boolean matchFound = m.matches();

	    if (!matchFound)
	    {
	    	check[4] = "No válida de correo electrónico";
	    }
	    new Replace();
		String keyword = Replace.remove(req.getParameter("keyword"));
		Text tag = new Text(keyword);
		insert.setKeyword(tag);
		
		insert.setLink(Replace.remove(req.getParameter("link")).replaceAll("\\<.*?\\>", ""));
		
		insert.setLinkEmbed(Replace.remove(req.getParameter("linkEmbed")).replaceAll("\\<.*?\\>", ""));
		insert.setTitle(req.getParameter("title").replaceAll("\\<.*?\\>", ""));
		if(req.getParameter("title") != null && req.getParameter("title").equals(""))
		{
			check[5] = "El título no está vacío";
		}
		
		//check capchar
		String remoteAddr = req.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LfG6cQSAAAAAETqJc2lHtwpCVyVP9VdqLFfuDNU");

        String challenge = req.getParameter("recaptcha_challenge_field");
        String uresponse = req.getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

        if (!reCaptchaResponse.isValid()) {
        	check[7] = "No válido Capchar";
        } 
		
		String alias = Replace.replace(req.getParameter("title"));
		insert.setAlias(alias);
		insert.setArticleId("");
		String authorAlias = Replace.replace(req.getParameter("author"));
		insert.setAuthorAlias(authorAlias);
		
		Query query_check = psm.newQuery(Articles.class);
		query_check.setFilter("alias=='"+alias+"'");
		@SuppressWarnings("unchecked")
		List<Articles> check_article = (List<Articles>) query_check.execute();
		
		if(check_article.size() > 0)
		{
			check[6] = "Título ya existe";
		}
		if(check[0] == null && check[1] == null && check[2] == null && check[3] == null && check[4] == null && check[5] == null && check[6] == null && check[7] == null)
		{
			psm.makePersistent(insert);
			Articles rearticle = new Articles();
			rearticle.setAlias(insert.getAlias());
			rearticle.setAuthor(insert.getAuthor());
			rearticle.setAuthorAlias(insert.getAuthorAlias());
			rearticle.setCategoryAlias(insert.getCategoryAlias());
			rearticle.setContent(insert.getContent());
			rearticle.setDate(insert.getDate());
			rearticle.setEmail(insert.getEmail());
			rearticle.setArticleId(String.valueOf(insert.getKey().getId()));
			rearticle.setKey(insert.getKey());
			rearticle.setKeyword(insert.getKeyword());
			rearticle.setLink(insert.getLink());
			rearticle.setLinkEmbed(insert.getLinkEmbed());
			rearticle.setTitle(insert.getTitle());
			rearticle.setView(insert.getView());
			rearticle.setVodeNo(0);
			rearticle.setVodeYes(0);
			psm.makePersistent(rearticle);
			
			if(req.getParameter("keyword") != null)
			{
				String[] tags = req.getParameter("keyword").split(",");
				for(int i=0;i<tags.length;i++)
				{
					@SuppressWarnings("static-access")
					String alias_key = new Replace().replace(tags[i]);
					Query query_keyword = psm.newQuery(Keyword.class);
					query_keyword.setFilter("alias=='"+alias_key+"'");
					@SuppressWarnings("unchecked")
					List<Keyword> keywords = (List<Keyword>) query_keyword.execute();
					if(keywords.size() > 0)
					{
						Keyword update = new Keyword();
						update.setKey(keywords.get(0).getKey());
						update.setAlias(keywords.get(0).getAlias());
						update.setName(keywords.get(0).getName());
						update.setDate(keywords.get(0).getDate());
						
						String check_alias = "abc";
						String list_keywords = String.valueOf(keywords.get(0).getKeyGame().getValue()); 
						String[] split_alias = list_keywords.split(",");
						for(int j=0;j<split_alias.length;j++)
						{
							if(split_alias.equals(insert.getKey().getId()))
							{
								check_alias = "";
							}
						}
						if(check_alias.equals("abc"))
						{
							Text str_keyword = new Text(keywords.get(0).getKeyGame().getValue()+","+insert.getKey().getId());
							update.setKeyGame(str_keyword);
						} else {
							Text str_keyword = new Text(keywords.get(0).getKeyGame().getValue());
							update.setKeyGame(str_keyword);
						}
						psm.makePersistent(update);
					} else {
						Keyword update = new Keyword();
						update.setAlias(alias_key);
						update.setName(tags[i]);
						String key = String.valueOf(insert.getKey().getId());
						Text str_keyword = new Text(key);
						update.setKeyGame(str_keyword);
						Date date_key = new Date();
						update.setDate(date_key);
						psm.makePersistent(update);
					}
				}
			}
			
			req.setAttribute("title", "Envíe el éxito");
			req.setAttribute("description", "Enviar artículos success.Free proporcionada por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
			req.setAttribute("keyword", "yooarticles.com,presente artículo libre, en el artículo, los artículos");
			try {
				req.getRequestDispatcher("/submit_complete.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//get list category
			Query query_category = psm.newQuery(Category.class);
			query_category.setOrdering("name ASC");
			@SuppressWarnings("unchecked")
			List<Category> list_category = (List<Category>) query_category.execute();
			req.setAttribute("list_category", list_category);
			
			//array save data when error
			String[] data = new String[9];
			data[0] = Replace.remove(req.getParameter("author"));
			data[1] = req.getParameter("email");
			data[2] = Replace.remove(req.getParameter("title"));
			data[3] = req.getParameter("categoryAlias");
			data[5] = req.getParameter("content");
			data[6] = Replace.remove(req.getParameter("keyword"));
			data[7] = Replace.remove(req.getParameter("link"));
			data[8] = Replace.remove(req.getParameter("linkEmbed"));
			
			req.setAttribute("check", check);
			req.setAttribute("data", data);
			
			req.setAttribute("title", "Envíe el error");
			req.setAttribute("description", "Enviar artículos error.Free proporcionada por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
			req.setAttribute("keyword", "yooarticles.com,presente artículo libre, en el artículo, los artículos");
			try {
				req.getRequestDispatcher("/submit.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
