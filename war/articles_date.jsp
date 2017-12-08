<%@page import="java.util.Date"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%
	Format formatter = new SimpleDateFormat("yyyy");
	Format formatter1 = new SimpleDateFormat("M");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
	<head>
		<title>Archivos - yooarticles.com</title>
		<meta name="description" content="Artículos gratis proporcionado por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación."/>
		<meta name="Keywords" content="yooarticles.com,presente artículo libre, en el artículo, los artículos"/>	
		<meta http-equiv="content-language" content="es">
		<meta content="text/html; charset=utf-8" http-equiv="content-type" />
		<link rel='stylesheet' type='text/css' href='/css/articles_date.css' />
		<link rel="alternate" type="application/rss+xml" title="yooarticles.com - RSS" href="/rss" />
	</head>
	<body>
		<div class="link_index"><a href="/">Yooarticles.com</a></div>
		<div class="headder"></div>
		<div class="content">
			<ul class="list">
				<%
					Date date = new Date();
					int end;
					String month = "";
					for(int i=Integer.parseInt(formatter.format(date));i>=2011;i--)
					{
						out.print("<li class=\"one\">"+i+"</li>");
						if(i == Integer.parseInt(formatter.format(date)))
						{
							end = Integer.parseInt(formatter1.format(date));
						} else {
							end = 12;
						}
						
						for(int j=end;j>=1;j--)
						{
							switch(j)
							{
								case 1:
									month = "January";
									break;
								case 2:
									month = "February";
									break;	
								case 3:
									month = "March";
									break;
								case 4:
									month = "April";
									break;
								case 5:
									month = "May";
									break;
								case 6:
									month = "June";
									break;
								case 7:
									month = "July";
									break;
								case 8:
									month = "August";
									break;
								case 9:
									month = "September";
									break;
								case 10:
									month = "October";
									break;
								case 11:
									month = "November";
									break;
								case 12:
									month = "December";
									break;	
							}
							out.print("<li><a href='/archives/"+j+"_"+i+"'>"+month+" "+i+"</a></li>");
						}
					}
				%>
			</ul>
		</div>
	</body>
</html>		