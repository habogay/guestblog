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


public class CrawArticleAlley {
	public CrawArticleAlley()
	{
		try {
			PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
			Query query = psm.newQuery(LinkCraw.class);
			query.setFilter("active=='1' && domain == 'articlealley.com'");
			query.setRange(0,1);
			List<LinkCraw> linkcraw = (List<LinkCraw>) query.execute();
			if(linkcraw.size()>0)
			{
				for(int i=0;i<linkcraw.size();i++)
				{
					LinkCraw tmp = linkcraw.get(i);
					URL dataURL = new URL(tmp.getLink().replaceAll("_1_", "_"+tmp.getStart()+"_"));

					LinkCraw update = new LinkCraw();
					update.setActive("0");
					update.setAliasCategory(tmp.getAliasCategory());
					update.setEnd(tmp.getEnd());
					update.setStart(tmp.getStart()+1);
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
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("iso-8859-1")));
					String content = "";
					while(1==1)
					{
						String str = reader.readLine();
						if(str==null) break;
						content+=str;
						
					}
					Document doc = Jsoup.parse(content);
					Elements element = doc.select("h2 a");
//					System.out.println(element);
					for(int j=0;j < element.size();j++)
					{
						String link = "http://www.articlealley.com/"+element.get(j).attr("href");
						String title = element.get(j).text();
//						System.out.println(link);
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
	public static void main(String[] args) {
		new CrawArticleAlley();

	}
}
