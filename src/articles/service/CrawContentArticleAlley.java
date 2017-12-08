package articles.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.appengine.api.datastore.Text;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;
import articles.model.Craw;
import articles.model.Keyword;
import articles.model.LinkCraw;
import articles.string.Replace;


public class CrawContentArticleAlley {
	public CrawContentArticleAlley()
	{
		try {
			PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
			Query query = psm.newQuery(Craw.class);
			query.setFilter("domain == 'articlealley.com'");
			query.setRange(0,1);
			List<Craw> craw = (List<Craw>) query.execute();
			if(craw.size()>0)
			{
				for(int i=0;i<craw.size();i++)
				{
					Craw tmp = craw.get(i);
					URL dataURL = new URL(tmp.getLink());

					HttpURLConnection connection = (HttpURLConnection) dataURL.openConnection();
					
					connection.setReadTimeout(500000);
					connection.setConnectTimeout(1000000);
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("iso-8859-1")));
					String content = "";
					while(1==1)
					{
						String str = reader.readLine();
						if(str==null) break;
						content+=str;
						
					}
					Document doc = Jsoup.parse(content);
					String str = doc.select("div[class=KonaBody]").html().replaceAll("<br />", "[br]").replaceAll("<a", "[[a").replaceAll("</a", "[[/a").replaceAll("\\<.*?\\>", "").replaceAll("\\[br\\]", "<br />").replaceAll("\\[\\[a", "<a").replaceAll("\\[\\[\\/a", "</a").trim().replaceAll("[\\n\\r]+", " ");
					
					
					String author = doc.select("div[id=hauth]").text().replaceAll("Author:", "").trim();
//					System.out.println(author);
					Text contents = new Text(str);
					String list_keyword = Replace.keyword(str,""); 
					Text keyword = new Text(list_keyword);
					
					Date date = new Date();
					
					new Replace();
					String alias = Replace.replace(tmp.getTitle());
					
					Query query_check = psm.newQuery(Articles.class);
					query_check.setFilter("alias=='"+alias+"'");
					@SuppressWarnings("unchecked")
					List<Articles> check_article = (List<Articles>) query_check.execute();
					
					if(check_article.size() <= 0)
					{
						Articles insert = new Articles();
						insert.setAlias(alias);
						insert.setArticleId("");
						insert.setAuthor(author);
						insert.setAuthorAlias(Replace.replace(author));
						insert.setCategoryAlias(tmp.getAliasCategory());
						insert.setContent(contents);
						insert.setDate(date);
						insert.setEmail(author.replaceAll(" ", "")+"@gmail.com");
						insert.setKeyword(keyword);
						insert.setLink("");
						insert.setLinkEmbed("");
						insert.setTitle(tmp.getTitle());
						insert.setView(0);
						insert.setVodeNo(0);
						Date vodeYes = new Date();
						insert.setVodeYes((int)vodeYes.getTime());
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
						
						psm.deletePersistent(tmp);
						if(list_keyword != null)
						{
							String[] tags = list_keyword.split(",");
							for(int k=0;k<tags.length;k++)
							{
								@SuppressWarnings("static-access")
								String alias_key = new Replace().replace(tags[k]);
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
									update.setName(tags[k]);
									String key = String.valueOf(insert.getKey().getId());
									Text str_keyword = new Text(key);
									update.setKeyGame(str_keyword);
									Date date_key = new Date();
									update.setDate(date_key);
									psm.makePersistent(update);
								}
							}
						}
					} else {
						psm.deletePersistent(tmp);
					}
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new CrawContentArticleAlley();

	}
}
