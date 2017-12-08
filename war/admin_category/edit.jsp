<%@page import="articles.model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		
	</head>
	<%
		List<Category> category = (List<Category>) request.getAttribute("category");
	%>
	<body>
		<div style="width: 570px;margin: 50px auto;">
			<form action="/posteditcategory" method="post">			
				<input type="text" name="name" style="width: 300px;" value="<%=category.get(0).getName() %>"/> Name<br/><br/>
				<input type="text" name="alias" style="width: 300px;" value="<%=category.get(0).getAlias() %>"/> Alias<br/><br/>
				<input type="hidden" name="old_alias" style="width: 300px;" value="<%=category.get(0).getAlias() %>"/>
				<input type="text" name="title" style="width: 300px;" value="<%=category.get(0).getTitle() %>"/> Title<br/><br/>
				<input type="text" name="keyword" style="width: 300px;" value="<%=category.get(0).getKeyword() %>"/> Keyword<br/><br/>
				<input type="text" name="order" style="width: 300px;" value="<%=category.get(0).getOrder() %>"/> Order<br/><br/>
				<textarea name="description" style="width: 450px;height: 150px;"><%=category.get(0).getDescription().getValue() %></textarea>
				Description<br/>
				<input type="submit" value="submit"/>			
			</form>
		</div>	
	</body>
</html>	