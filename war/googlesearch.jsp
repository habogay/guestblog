<%@page contentType="text/html;charset=UTF-8" language="java"%>
<% request.setCharacterEncoding("utf-8");%>
<%
	String seo_title = (String)request.getAttribute("seo_title");
	String seo_description = (String) request.getAttribute("seo_description");	
	String title = (String) request.getAttribute("title");
	String description = (String) request.getAttribute("description");
	String keywords = (String) request.getAttribute("keyword");
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
				<hr size="1"/>
				<div id="cse" style="width: 100%;">Cargando ...</div>
				<script src="http://www.google.com/jsapi" type="text/javascript"></script>
				<script type="text/javascript">
				  google.load('search', '1', {language : 'en'});
				  google.setOnLoadCallback(function() {
				    var customSearchControl = new google.search.CustomSearchControl('009050056988840974278:rtnurbnv1-m');
				    customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);
				    customSearchControl.draw('cse');
				  }, true);
				</script>
				<link rel="stylesheet" href="http://www.google.com/cse/style/look/default.css" type="text/css" />
			</div>
			<div class="related">
				<ul class="show_t_r">
					<jsp:include page="element/new_tag.jsp"></jsp:include>
					<jsp:include page="element/top_view.jsp">
						<jsp:param name="alias" value=""/>
					</jsp:include>
					<jsp:include page="element/top_vote.jsp">
						<jsp:param name="alias" value=""/>
					</jsp:include>
				</ul>
			</div>
			<div class="clear"></div>
		</div>

<%@ include file='/layout/footer.jsp'%>
<style>.gsc-adBlock{display: none;}</style>