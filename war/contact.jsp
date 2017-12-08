<%@page import="articles.string.Replace"%>
<%@page import="articles.model.Category"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="articles.model.Articles"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>

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
					<li>
						If you have any questions, or comments and suggestions, please use the form below to contact us. Or you can mail to contact [at] yooarticles.com .
						<br/><br/>
						<form action="/sendmail" method="post" class="send_mail">
							Your name :<br/>
							<input  type="text" name="name"/>
							Your email :<br/>
							<input  type="text" name="email"/>
							Message :<br/>
							<textarea name="message"></textarea>
							<button type="submit">Send</button>
						</form>
					</li>
				</ul>
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