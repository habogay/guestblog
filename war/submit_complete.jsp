<%@page import="articles.model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<% request.setCharacterEncoding("utf-8");%>
<%
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
			
			<jsp:include page="element/left.jsp">
				<jsp:param name="title_url" value="abc"/>
			</jsp:include>
			
			<div class="show">
				<h2>Envíe el éxito</h2>
				<jsp:include page="element/adv_one.jsp"></jsp:include>
				<hr size="1"/>
				<br/>
				<div class="info_author">
				<center><jsp:include page="element/adv_four.jsp"></jsp:include></center>
				</div>
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