<%@page import="articles.string.Replace"%>
<%@page import="java.util.Formatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="articles.model.Articles"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<% request.setCharacterEncoding("utf-8");%>
<%
	Format formatter = new SimpleDateFormat("MM-dd-yy");
	Format formatter1 = new SimpleDateFormat("MM_yyyy");
	List<Articles> article = (List<Articles>) request.getAttribute("article");
	String title = (String) request.getAttribute("title");
	String description = (String) request.getAttribute("description");
	String keywords = (String) request.getAttribute("keyword");
	List<Articles> list_article = (List<Articles>) request.getAttribute("list_article");
%>
<jsp:include page="layout/header.jsp">
	<jsp:param name="title" value="<%=title %>"/>
	<jsp:param name="description" value="<%=description %>"/>
	<jsp:param name="keywords" value="<%=keywords %>"/>
</jsp:include>
		<div class="content">
			
			<jsp:include page="element/left.jsp">
				<jsp:param name="title_url" value="abc"/>
			</jsp:include>
			
			<div class="show">
				<h1><%=article.get(0).getTitle() %></h1>	
				<strong>Autor : </strong><span class="author"><a href="/author/<%=article.get(0).getAuthorAlias() %>" title="Autor <%=Replace.remove(article.get(0).getAuthor()) %>"><%=article.get(0).getAuthor() %></a></span> | 
				<strong>Fecha : </strong><span class="author"><a href="/date/<%=formatter1.format(article.get(0).getDate()) %>" class="link_author" title="Fecha <%=formatter.format(article.get(0).getDate()) %>"><%=formatter.format(article.get(0).getDate()) %></a></span> |
				<strong>Ver : </strong><span class="author"><%=article.get(0).getView() %></span>
				<hr size="1"/>
				<ul class="list_articles">
					<li class="show_content">
						<div class="adv_three">
							<jsp:include page="element/adv_three.jsp"></jsp:include>
						</div>
						<div class="adv_content">
							<jsp:include page="element/adv_three.jsp"></jsp:include>
						</div>
						<%
							String value = article.get(0).getContent().getValue().replaceAll("^[<]+[(br)]+[ ]+[/]+[>]+","").replaceAll("^[<]+[(br)]+[ ]+[/]+[>]+","");
							value = value.replace("%2B"," ");
							value = value.replace("%253C","<");
							value = value.replace("%252F","/");
							value = value.replace("%252C",",");
							value = value.replace("%253E",">");
							if(value.indexOf("<p>") != -1)
							{
								out.println(value);
							} else {
						%>
								<p><%=value %></p>
						<%
							}
						%>
					</li>
				</ul>
				<div class="info_author">
					<div class="left"><strong>Idioma : &nbsp;</strong></div>
					<div class="left">
						<a href="http://www.yooarticles.com/article/<%=article.get(0).getAlias()%>" title="<%=Replace.remove(article.get(0).getTitle())%>"><img src="/image/flag_english.gif" alt="Inglés"></img></a>
						<a href="http://es.yooarticles.com/article/<%=article.get(0).getAlias()%>" title="<%=Replace.remove(article.get(0).getTitle())%>"><img src="/image/flag_Spanish.png" alt="Español"></img></a>
						<a href="http://pt.yooarticles.com/article/<%=article.get(0).getAlias()%>" title="<%=Replace.remove(article.get(0).getTitle())%>"><img src="/image/pt.png" alt="portugués"></img></a>
					</div> 
					<div class="clear"></div>
				</div>
				<div class="info_author">
					<%
						if(!article.get(0).getLink().equals(""))
						{
					%>
							<strong>Enlace al sitio web :</strong> <a href="<%=article.get(0).getLink() %>"><%=article.get(0).getLink() %></a><br/>
					<%
						}
						if(!article.get(0).getLinkEmbed().equals(""))
						{
					%>
							<strong>Enlace de vídeo : </strong><a href="<%=article.get(0).getLinkEmbed() %>"><%=article.get(0).getLinkEmbed() %></a>
					<%
						}
					%>
				</div>
				<div class="tags">
					<strong>Etiquetas : </strong> 
					<%
						if(article.get(0).getKeyword() != null)
						{	
							String[] tags = article.get(0).getKeyword().getValue().split(",");
							for(int i=0;i<tags.length;i++)
							{
								String alias = new Replace().replace(tags[i]);
								if(i != 0)
									out.print(" , ");
					%>
								<a href="/tags/<%=alias %>" title="Etiquetas <%=tags[i] %>"><%=tags[i] %></a>
					<%			
							}
						}
					%>
				</div>
				<div class="info_addon">
					<div class="left">
						<script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>
						<g:plusone size="midium" count="true"></g:plusone>
					</div>
					<!-- AddThis Button BEGIN -->
					<div class="addthis_toolbox addthis_default_style ">
					<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
					<a class="addthis_button_tweet"></a>
					<a class="addthis_counter addthis_pill_style"></a>
					</div>
					<script type="text/javascript">var addthis_config = {"data_track_clickback":true};</script>
					<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4d6f3cdb1d190d60"></script>
					<!-- AddThis Button END -->
					<div class="clear"></div>
				</div>
				<div id="fb-root"></div><script src="http://connect.facebook.net/en_US/all.js#appId=APP_ID&amp;xfbml=1"></script><fb:comments href="http://www.yooarticles.com/article/<%=article.get(0).getAlias() %>" width="560"></fb:comments>
			</div>	
			<div class="related">
				<ul class="show_t_r">
				
					<jsp:include page="element/new_tag.jsp">
						<jsp:param name="title_url" value="abc"/>
					</jsp:include>
					
					<%
						if(list_article.size()>0)
						{
					%>
							<li class="one">Los artículos del autor '<%=article.get(0).getAuthor() %>'</li>
							<li>
								<ol class="list_r">
									<%							
										for(int i=0;i<list_article.size();i++)
										{
											Articles tmp = list_article.get(i); 
									%>
											<li><a href="/article/<%=tmp.getAlias() %>" title="Artículo <%=Replace.remove(tmp.getTitle()) %>"><%=tmp.getTitle() %></a></li>
									<%
										}								
									%>
								</ol>
							</li>
					<%
						}
					%>
					<jsp:include page="element/top_view.jsp">
						<jsp:param name="title_url" value="abc"/>
					</jsp:include>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
<%@ include file='/layout/footer.jsp'%>