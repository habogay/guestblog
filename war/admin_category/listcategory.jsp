<%@page import="articles.model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%
	List<Category> listcategory = (List<Category>) request.getAttribute("listcategory");

	for(int i=0;i< listcategory.size();i++)
	{
		Category tmp = listcategory.get(i);
		out.println("<a href='/editcategory/"+tmp.getAlias()+"'>"+tmp.getName()+"</a><br/>");
	}
%>


