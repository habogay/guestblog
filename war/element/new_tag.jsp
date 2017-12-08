<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>
<%@page import="javax.jdo.Query"%>
<%@page import="articles.model.Keyword"%>
<%@page import="articles.data.QnAPersistenceManager"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="articles.string.Replace"%>
<%
	//get list category
	PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
	Query query_keyword = psm.newQuery(Keyword.class);
	query_keyword.setOrdering("date DESC");
	query_keyword.setRange(0,15);
	@SuppressWarnings("unchecked")
	List<Keyword> list_keyword = (List<Keyword>) query_keyword.execute();
	Random generator = new Random();
%>
<li class="one">Nuevas etiquetas</li>
<li class="tag">
	<%
	if(list_keyword.size() > 0)
	{
		for(int i=0;i<list_keyword.size();i++)
		{
			Keyword tmps = list_keyword.get(i);
			if(i != 0)
			{
				out.println(",");
			}
	%>
			<a class="tag<%=generator.nextInt(4) %>" href="/tags/<%=tmps.getAlias() %>" title="Etiquetas <%=Replace.remove(tmps.getName()) %>"><%=tmps.getName() %> </a>
	<%
		}
	}
	%>
</li>