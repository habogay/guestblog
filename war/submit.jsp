<%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@page import="articles.model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<% request.setCharacterEncoding("utf-8");%>
<%
	List<Category> list_category = (List<Category>)request.getAttribute("list_category");
	String[] check = (String[]) request.getAttribute("check");
	String[] data = (String[]) request.getAttribute("data");
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
				<h2>Enviar un artículo</h2>
				<jsp:include page="element/adv_one.jsp"></jsp:include>
				<hr size="1"/>
				<p>Envíe su artículo a <b> Yooarticles.com </b> llenando el siguiente formulario. Por favor, incluya una breve biografía de sí mismo que pone de relieve su campo de experiencia, experiencia, nombre de la empresa (si procede), dirección del sitio web y su dirección de e-mail. Estas información se utilizará para el "Sobre el autor" caja. Por favor, también tome tiempo para leer el acuerdo del autor antes de enviar su artículo.</p>	
				<hr size="1"/>
				<form action="/post-articles" method="post">
					<ul class="submit">
						<li>
							<h3>Nombre completo del autor:</h3>
							<input name="author" type="text" value="<%=(data[0] != null)?data[0]:"" %>" <%=(check[0] != null)?"style='border:1px solid red;'":"" %>/>
							<%=(check[0] != null)?"<p style='color:red;'>"+check[0]+"</p>":"" %>
						</li>
						<li>
							<h3>Autor de la Dirección E-mail:</h3>
							<input name="email" type="text" value="<%=(data[1] != null)?data[1]:"" %>" <%=(check[4] != null)?"style='border:1px solid red;'":"" %>/>
							<%=(check[4] != null)?"<p style='color:red;'>"+check[4]+"</p>":"" %>
						</li>
						<li>
							<h3>Título del artículo:</h3>
							<input name="title" type="text" value="<%=(data[2] != null)?data[2]:"" %>" <%=(check[5] != null || check[6] != null)?"style='border:1px solid red;'":"" %>/>
							<%=(check[5] != null)?"<p style='color:red;'>"+check[5]+"</p>":"" %>
							<%=(check[6] != null)?"<p style='color:red;'>"+check[6]+"</p>":"" %>
						</li>
						<li>
							<h3>Coloque este artículo en qué categoría? </h3>
							<select size="6" name="categoryAlias" <%=(check[1] != null)?"style='border:1px solid red;'":"" %>>
								<option value="" selected="selected">Elija una</option>
								<option value="">---------------------------------------------------------------------</option>
								<%
									if(list_category.size()>0)
									{
										for(int i=0;i<list_category.size();i++)
										{
											if(data[3] != null && data[3].equals(list_category.get(i).getAlias()))
											{
												out.println("<option value='"+list_category.get(i).getAlias()+"' selected='selected'>"+list_category.get(i).getName()+"</option>");
											} else {
												out.println("<option value='"+list_category.get(i).getAlias()+"'>"+list_category.get(i).getName()+"</option>");
											}
										}
									}
								%>
							</select>
							<%=(check[1] != null)?"<p style='color:red;'>"+check[1]+"</p>":"" %>
						</li>
						<li>
							<h3>Contenido:</h3>
							<textarea class="a_c" name="content" <%=(check[2] != null)?"style='border:1px solid red;'":"" %>><%=(data[5] != null)?data[5]:""%></textarea>
							<%=(check[2] != null)?"<p style='color:red;'>"+check[2]+"</p>":"<p>Mínimo de 500 caracteres</p>" %>
						</li>
						<li>
							<h3>Palabra clave: </h3>
							<input name="keyword" type="text" value="<%=(data[6] != null)?data[6]:"" %>"/>
							<p>Un máximo de 5 palabras clave, por ejemplo: palabra1, palabra2, ...</p>
						</li>
						<li>
							<h3>Dirección del sitio web del autor: </h3>
							<input name="link" type="text" value="<%=(data[7] != null)?data[7]:"" %>"/>
						</li>
						<li>
							<h3>Enlace de video: </h3>
							<input name="linkEmbed" type="text" value="<%=(data[8] != null)?data[8]:"" %>"/>
						</li>
						<li>
							<script>
					        	var RecaptchaOptions = { theme : 'clean' };
					  		</script>
							<%
								//6LdXmcISAAAAAHz6FsABghup-EayldUCiKQyDPAk //public
								//6LdXmcISAAAAANPIv5ykGg3LAlBMnUQHzy3-Kiqo //private
					          ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LfG6cQSAAAAAPMhX6aFr4LfZH3daYoW7DvA495j", "6LfG6cQSAAAAAETqJc2lHtwpCVyVP9VdqLFfuDNU", false);
					          out.print(c.createRecaptchaHtml(null, null));
					        %>
					        <%=(check[7] != null)?"<p style='color:red;'>"+check[7]+"</p>":"" %>
						</li>
						<li>
							<input type="submit" value="Enviar artículos"/>
						</li>
					</ul>
				</form>
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
<script type="text/javascript" src="/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/js/mi_submit.js"></script>
<%@ include file='/layout/footer.jsp'%>   