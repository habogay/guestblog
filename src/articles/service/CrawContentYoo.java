package articles.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.appengine.api.datastore.Text;

import articles.data.QnAPersistenceManager;
import articles.model.Articles;
import articles.model.Craw;
import articles.model.Keyword;

import articles.string.Replace;
import articles.string.TimeStam;


public class CrawContentYoo {
	public CrawContentYoo(int start)
	{
		try {
			PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
			Query query = psm.newQuery(Craw.class);
			query.setFilter("domain == 'yooarticles.com'");
			query.setRange(start,start+1);
			@SuppressWarnings("unchecked")
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
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8")));

					String content = "";
					while(1==1)
					{
						String str = reader.readLine();
						if(str==null) break;
						content+=str;
						
					}
					Document doc = Jsoup.parse(content);
					String author = "guest";
					if(doc.select(".author").size() > 0)
						author = doc.select(".author a").get(0).text().trim();
					String link = "";
					link = doc.select(".box .link").text();
					String aliasCategory = doc.select(".hidden_category").text();
					
					String list_keyword = null;
					Elements keywordss = doc.select(".ta"); 
					for(int j=0;j<keywordss.size();j++)
					{
						if(j == 0)
						{
							list_keyword = keywordss.get(j).text();
						} else {
							list_keyword += ","+keywordss.get(j).text();
						}
					}
					reader.close();
					doc.select(".adv-3").remove();
					doc.select(".adv-1").remove();
					doc.select(".adv-2").remove();
					String str = doc.select("details").html();
					
					if(str.equals(""))
					{
						psm.deletePersistent(tmp);
					}
					str = str.replaceAll("<a[^>]+>", "").replaceAll("<\\/[ ]*a>", "");

					str = Translate(str, "es");
					

					Text contents = new Text(str);

					
					str = tmp.getTitle();
					
					
					String title = str = Translate(str, "es");;
					
					System.out.println(title);
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
						insert.setCategoryAlias(aliasCategory);
						insert.setContent(contents);
						insert.setDate(date);
						insert.setEmail(author.replaceAll(" ", "")+"@gmail.com");
						insert.setKeyword(keyword);
						insert.setLink(link);
						insert.setLinkEmbed("");
						insert.setTitle(title);
						insert.setView(0);
						insert.setVodeNo(0);
						insert.setVodeYes(TimeStam.time());
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
						rearticle.setVodeYes(insert.getVodeYes());
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
	public static String Translate(String string,String language) throws IOException
	{
		string = URLEncoder.encode(string.replaceAll("[\\']{1}", "\\\'").replaceAll("[\\\"]{1}", "\\\""), "UTF-8");
		URL url = new URL("http://translate.google.com/?hl=en&tab=TT");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.21) Gecko/20110830 AlexaToolbar/alxf-2.14 AlexaToolbar/pXVUnvuf-1.2 Firefox/3.6.21");
		connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("Accept-Language", "en-us,en;q=0.5");
		connection.setRequestProperty("Accept-Charset", "	ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.setRequestProperty("Keep-Alive", "115");
		connection.setRequestProperty("Cookie", "PREF=ID=987bb7a56b1cc7bc:U=691fa246d56f502e:FF=0:TM=1314876638:LM=1314876681:S=yBXzqp_WQq9ykX-j; NID=50=nfNXg7ymSSsfdzMnkEuo7t1GCgIEwcK5xmpFhu1mQWwvNI5nFHLZpRsy_1XnaoKrdj9hnz7za9-M2OIO99phfFSTmXln-xCvqzJaRWHsN0EQKgpmkvgUDTWPVF4I4c9L; SID=DQAAALcAAADhqr-J8Ls3CKhmbRjwTMMP1DjmIvR8qBFxv99saTaFFgoRsVf18W4p_BTocYoxYNMvsvh9sfjMj7KqLyd_s9Y0lwYY_Rrljd7t5IpKBTWWnsv9VSJ_qelH943zV8YIT3uuIdJbHhFlCnVEu_YealZoldWjf4VirYR0PIrr0i4_JU2JCHRmpyW0ACt3KcyLkukJlGDyT5yC5js3CBSTAhnWSylBpMWPWyWJzqXe2BQFcdfuU_qo00pAR-uEcMQ437M; HSID=A9Z-j3CKhzDEykAES");
		connection.setRequestProperty("Cache-Control", "max-age=0");
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write("text=" + string);
        writer.write("&eotf=1");
        writer.write("&hl=vi");
        writer.write("&ie=UTF-8");
        writer.write("&js=n");
        writer.write("&layout=2");
        writer.write("&prev=_t");
        writer.write("&sl=en");
        writer.write("&tl="+language);
        writer.close();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        	String content = "";			
        	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
			while(1==1)
			{
				String str = reader.readLine();
				if(str==null) break;
				content+=str;				
			}
			Document doc = Jsoup.parse(content);
			string = doc.select("#result_box").text().replaceAll("<[ ]*/[ ]+", "</");
        }
        return string;
	}
//	public static void main(String[] args) {
//		new CrawContentYoo();
//
//	}
}
