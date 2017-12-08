
<%@page import="articles.model.Articles"%>
<%@ page contentType="text/xml;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>
<?xml version="1.0" encoding="UTF-8"?>
<urlset
      xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
  <url>
  <loc>http://yoospain.appspot.com/</loc>
</url>
<%
List<Articles> listArticles= (List<Articles> )request.getAttribute("listArticles");
for(Articles article : listArticles)
{
%>
  <url>
  <loc>http://yoospain.appspot.com/article/<%=article.getAlias()%></loc>
</url>

<%

}
%>

</urlset>