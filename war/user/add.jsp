<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	</head>
	<body>
		<div style="width: 450px;margin: 50px auto;">
			<form action="/postuser" method="post">	
				<input name="username"/>Username<br/>
				<select name="group">
					<option value="">-- Choose group --</option>
					<option value="1">Admin</option>
					<option value="2">Management</option>
				</select>
				<br/><br/>
				<input type="submit" value="Add"/>
			</form>
		</div>	
	</body>
</html>	