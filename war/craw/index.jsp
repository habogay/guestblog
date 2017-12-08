<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		
	</head>
	<body>
		<div style="width: 570px;margin: 50px auto;">
			<form action="/postlinkcraw" method="post">			
				<input type="text" name="link" style="width: 300px;"/> Link<br/><br/>
				<input type="text" name="aliasCategory" style="width: 300px;"/> Alias Category<br/><br/>
				<input type="text" name="start" style="width: 300px;"/> start<br/><br/>
				<input type="text" name="end" style="width: 300px;"/> end<br/><br/>
				<input type="text" name="domain" style="width: 300px;"/> Domain<br/><br/>
				<input type="submit" value="submit"/>			
			</form>
		</div>	
	</body>
</html>	