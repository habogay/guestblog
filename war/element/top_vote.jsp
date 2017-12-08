<%@page import="java.util.List"%>
<%@page import="articles.model.Articles"%>
<%@page import="javax.jdo.Query"%>
<%@page import="articles.data.QnAPersistenceManager"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="articles.string.Replace"%>
<%
	PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
	Query query_article = psm.newQuery(Articles.class);
	String alias = (String)request.getParameter("alias");
	if(!alias.equals(""))
	{
		query_article.setFilter("categoryAlias=='"+alias+"'");
	}
	query_article.setOrdering("vodeYes asc");
	query_article.setRange(0,7);
	List<Articles> top_article = (List<Articles>) query_article.execute();
%>
<li class="one">Sitio al azar</li>
<li>
	<ol class="list_r">
		<%
			if(top_article.size()>0)
			{
				for(int i=0;i<top_article.size();i++)
				{
					Articles tmp = top_article.get(i);
		%>
					<li><a href="/article/<%=tmp.getAlias() %>" title="Artículo <%=Replace.remove(tmp.getTitle()) %>"><%=tmp.getTitle() %></a></li>
		<%
				}
			}
		%>
	</ol>
</li>