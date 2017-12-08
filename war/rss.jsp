<%@page import="java.util.List"%>
<%@page import="articles.model.Articles"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
  	List<Articles> listArticles = (List<Articles>) request.getAttribute("listArticles");
	String title = (String) request.getAttribute("title");
	String description = (String) request.getAttribute("description");
%>
<?xml version="1.0" encoding="UTF-8"?>
  <rss version="2.0"
    xmlns:dc="http://purl.org/dc/elements/1.1/"
    xmlns:sy="http://purl.org/rss/1.0/modules/syndication/"
    xmlns:admin="http://webns.net/mvcb/"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:content="http://purl.org/rss/1.0/modules/content/">

    <channel>
    
    <title><![CDATA[ <%=title %>]]></title>
    <link>http://yoospain.appspot.com/</link>
    <description><![CDATA[<%=description %>]]></description>
    <dc:language>es</dc:language>
    <dc:creator>google appengie</dc:creator>

    <dc:rights>Copyright 2011</dc:rights>
    <admin:generatorAgent rdf:resource="http://yoospain.appspot.com/" />
    	<%
    		for(int i=0;i<listArticles.size();i++)
    		{
    			Articles tmp = listArticles.get(i);
    			String des = tmp.getContent().getValue().replaceAll("\\<.*?\\>", "");
    			if(des.length() > 250)
    			{
    				des = des.substring(0,250);
    			}
    	%>
		       <item>
		          <title><![CDATA[ <%=tmp.getTitle()%> ]]></title>
		          <link>http://yoospain.appspot.com/article/<%=tmp.getAlias() %></link>
		          <guid><%=tmp.getTitle()%></guid>
		
		          <description><![CDATA[ <%=des %>]]></description>
		      	  <pubDate><%=tmp.getDate().toGMTString() %></pubDate>
		       </item>
       <%
    		}
       %>
	        
	    
    </channel>
</rss>  