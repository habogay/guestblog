<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="javax.cache.CacheException"%>
<%@page import="javax.cache.CacheManager"%>
<%@page import="javax.cache.Cache"%>
<%@page import="java.util.List"%>
<%@page import="articles.model.Articles"%>
<%@page import="javax.jdo.Query"%>
<%@page import="articles.data.QnAPersistenceManager"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="articles.string.Replace"%>
<%
Cache cache=null;

try {
    cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
} catch (CacheException e) {
   e.printStackTrace();
   
}
%>
<li class="one">Ver artículos recientes</li>
<li>
	<ol class="list_r">
		<%
            			if(cache!=null&&cache.containsKey("lastPing"))
    					{
            				ArrayList<String[]> lastView = (ArrayList<String[]>)cache.get("lastPing");
    						for(String[] bisiness : lastView) 
							{
            			%>
							<li><a href="/article/<%=bisiness[1] %>" title="Article <%=Replace.remove(bisiness[0]) %>"><%=bisiness[0] %></a></li>
		<%
							}
    					}
            			%>
	</ol>
</li>