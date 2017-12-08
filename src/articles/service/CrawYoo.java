package articles.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import articles.data.QnAPersistenceManager;
import articles.model.Craw;
import articles.model.LinkCraw;


public class CrawYoo {
	public CrawYoo(String name)
	{
		try {
			PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
			Query query = psm.newQuery(LinkCraw.class);
			query.setFilter("active=='1'");
			query.setRange(0,1);
			List<LinkCraw> linkcraw = (List<LinkCraw>) query.execute();
			if(linkcraw.size()>0)
			{
				for(int i=0;i<linkcraw.size();i++)
				{
					LinkCraw tmp = linkcraw.get(i);
					URL dataURL = new URL(tmp.getLink());

					LinkCraw update = new LinkCraw();
					update.setActive("0");
					update.setAliasCategory(tmp.getAliasCategory());
					update.setEnd(tmp.getEnd());
					update.setStart(tmp.getStart());
					update.setLink(tmp.getLink());
					update.setKey(tmp.getKey());
					update.setDomain(tmp.getDomain());
					if(tmp.getStart() == tmp.getEnd())
					{
						update.setActive("0");
					} else {
						update.setActive("1");
					}
					psm.makePersistent(update);
		
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
					Elements element = doc.select(".title_articles");
					
					for(int j=0;j < element.size();j++)
					{
						String link = "http://www.yooarticles.com"+element.get(j).attr("href");
						String title = element.get(j).text();
//						System.out.println(link);
//						System.out.println(title);
						Craw insert = new Craw();
						insert.setAliasCategory(tmp.getAliasCategory());
						insert.setLink(link);
						insert.setTitle(title);
						insert.setDomain(tmp.getDomain());
						psm.makePersistent(insert);
					}
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		new CrawYoo();
//
//	}
}
