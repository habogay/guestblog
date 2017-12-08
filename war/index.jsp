<%@page import="articles.string.Replace"%>
<%@page import="articles.model.Category"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="articles.model.Articles"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<% request.setCharacterEncoding("utf-8");%>
<%
	String seo_title = (String)request.getAttribute("seo_title");
	String seo_description = (String) request.getAttribute("seo_description");
	List<Articles> listArticles = (List<Articles>) request.getAttribute("listArticles");	
	String url = (String) request.getAttribute("url");
	String cur_page = (String) request.getAttribute("page");
	String title = (String) request.getAttribute("title");
	String description = (String) request.getAttribute("description");
	String keywords = (String) request.getAttribute("keyword");
	List<Category> category = (List<Category>)request.getAttribute("category");
%>
<jsp:include page="layout/header.jsp">
	<jsp:param name="title" value="<%=title %>"/>
	<jsp:param name="description" value="<%=description %>"/>
	<jsp:param name="keywords" value="<%=keywords %>"/>
</jsp:include>
		<div class="content">
			
			<jsp:include page="element/left.jsp"></jsp:include>
			
			<div class="show">
				<h2><%=seo_title %></h2>
				<jsp:include page="element/adv_one.jsp"></jsp:include>
				<hr size="1"/>
				<ul class="list_articles">
					<%
						Format formatter = new SimpleDateFormat("MM-dd-yy");
						Format formatter1 = new SimpleDateFormat("MM_yyyy");
						if(listArticles.size()>0)
						{
							for(int i=0;i<listArticles.size();i++)
							{
								Articles tmp = listArticles.get(i);
								if(i==4)
								{
					%>
									<li><jsp:include page="element/adv_four.jsp"></jsp:include></li>
					<%
								}
					%>
								<li>
									<a href="/article/<%=tmp.getAlias() %>" title="Artículo <%=Replace.remove(tmp.getTitle()) %>"><%=tmp.getTitle() %></a>
									<p>
										<%
											String value = tmp.getContent().getValue();
											value = value.replace("%2B"," ");
											value = value.replace("%253C","<");
											value = value.replace("%252F","/");
											value = value.replace("%252C",",");
											value = value.replace("%253E",">");
										
											if(value.replaceAll("\\<.*?\\>", "").length()>170)
											{
												out.println(value.replaceAll("\\<.*?\\>", "").substring(0,170)+" ...");
											} else {
												out.println(value.replaceAll("\\<.*?\\>", ""));
											}
										%>
									</p>
									<strong>Autor : </strong>
									<a href="/author/<%=tmp.getAuthorAlias() %>" class="link_author" title="Autor <%=Replace.remove(tmp.getAuthor()) %>"><%=tmp.getAuthor() %></a> | <strong>Fecha : </strong>
									<span><a href="/date/<%=formatter1.format(tmp.getDate()) %>" class="link_author" title="Date <%=formatter.format(tmp.getDate()) %>"><%=formatter.format(tmp.getDate()) %></a></span>
								</li>
					<%
							}
						}
					%>
					<li class="paginate">
						<jsp:include page="paginate.jsp">
							<jsp:param name="cur_page" value="<%=cur_page %>"/>
							<jsp:param name="url" value="<%=url %>"/>
							<jsp:param name="number_game" value="<%=listArticles.size() %>"/>
						</jsp:include>
					</li>
				</ul>
			</div>
			<div class="related">
				<ul class="show_t_r">
					<%
						String alias = "";
						if(category != null)
						{
							alias = category.get(0).getAlias();
						}
					%>
					<jsp:include page="element/new_tag.jsp"></jsp:include>
		
					<jsp:include page="element/top_view.jsp">
						<jsp:param name="alias" value="<%=alias %>"/>
					</jsp:include>
					<jsp:include page="element/top_vote.jsp">
						<jsp:param name="alias" value="<%=alias %>"/>
					</jsp:include>
				</ul>
			</div>
			<div class="clear"></div>
		</div>

<%@ include file='/layout/footer.jsp'%>