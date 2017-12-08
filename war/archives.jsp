<%@page import="articles.model.Articles"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%
	Format formatter = new SimpleDateFormat("MM-dd-yyyy");
	List<Articles> listArticles = (List<Articles>) request.getAttribute("listArticles");
	String url = (String) request.getAttribute("url");
	String cur_page = (String) request.getAttribute("page");
	String title = (String) request.getAttribute("title");
	String description = (String) request.getAttribute("description");
	String keywords = (String) request.getAttribute("keyword");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
	<head>
		<title><%=title %></title>
		<meta name="description" content="<%=description%>"/>
		<meta name="Keywords" content="<%=keywords%>"/>	
		<meta http-equiv="content-language" content="es">
		<meta content="text/html; charset=utf-8" http-equiv="content-type" />
		<link rel='stylesheet' type='text/css' href='/css/articles_date.css' />
		<link rel="alternate" type="application/rss+xml" title="yooarticles.com - RSS" href="/rss" />
	</head>
	<body>
		<div class="link_index"><a href="/">Yooarticles.com</a></div>
		<div class="headder"></div>
		<div class="back"><a href="/archives">Index of /YooArticles</a></div>
		<div class="content">
			<ol class="list_articles" start="<%=(Integer.parseInt(cur_page)-1)*100 + 1%>">
				<%
					for(int i=0;i<listArticles.size();i++)
					{
						out.println("<li><a href='/article/"+listArticles.get(i).getAlias()+"'>"+listArticles.get(i).getTitle()+" <span>( "+formatter.format(listArticles.get(i).getDate())+" )</span> "+"</a></li>");
					}
				%>
			</ol>
			<ul class="paginate">
				<li>
					<jsp:include page="re_paginate.jsp">
						<jsp:param name="cur_page" value="<%=cur_page %>"/>
						<jsp:param name="url" value="<%=url %>"/>
						<jsp:param name="number_game" value="<%=listArticles.size() %>"/>
					</jsp:include>
				</li>
			</ul>
		</div>
	</body>
</html>		