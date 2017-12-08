<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		
	</head>
	<body>
		<div style="width: 570px;margin: 50px auto;">
			<form action="/addcategory" method="post">			
				<input type="text" name="name" style="width: 300px;"/> Name<br/><br/>
				<input type="text" name="alias" style="width: 300px;"/> Alias<br/><br/>
				<input type="text" name="title" style="width: 300px;"/> Title<br/><br/>
				<input type="text" name="keyword" style="width: 300px;"/> Keyword<br/><br/>
				<input type="text" name="order" style="width: 300px;"/> Order<br/><br/>
				<textarea name="description" style="width: 450px;height: 150px;"></textarea>
				Description<br/>
				<input type="submit" value="submit"/>			
			</form>
		</div>	
	</body>
</html>	