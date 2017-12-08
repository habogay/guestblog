<%@page import="articles.string.Replace"%>
<%@page import="java.util.List"%>
<%@page import="articles.model.Category"%>
<%@page import="articles.data.QnAPersistenceManager"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="javax.jdo.Query"%>
<div class="list">
	<ul class="child_list">
		<li class="one">Sitio menú</li>
		<li><a href="/" title="Casa">Casa</a></li>
		<li><a href="/search" title="Búsqueda en Google">Búsqueda</a></li>
		<li><a href="/archives" title="Archives">Archivos</a></li>
		<li><a href="/submit-articles" title="Enviar un artículo">Enviar un artículo</a></li>
		<li><a href="/rss" title="Los últimos artículos rss">Los últimos artículos (RSS)</a></li>
	</ul>
	<ul class="child_list">
		<li class="one">Categoría</li>
		<li><a title="Categoría Auto y Camiones" href="/category/auto-trucks">Auto y Camiones</a></li>
<li><a title="Categoría Negocios y Finanzas" href="/category/business-finance">Negocios y Finanzas</a></li>
<li><a title="Categoría Computadoras e Internet" href="/category/computers-internet">Computadoras e Internet</a></li>
<li><a title="Categoría Educación" href="/category/education">Educación</a></li>
<li><a title="Categoría Medio Ambiente y Going Green" href="/category/environment-and-going-green">Medio Ambiente y Going Green</a></li>
<li><a title="Categoría Familia" href="/category/family">Familia</a></li>
<li><a title="Categoría Comida y bebida" href="/category/food-drink">Comida y bebida</a></li>
<li><a title="Categoría Adminículos y trastos" href="/category/gadgets-and-gizmos">Adminículos y trastos</a></li>
<li><a title="Categoría Salud" href="/category/health">Salud</a></li>
<li><a title="Categoría Aficiones" href="/category/hobbies">Aficiones</a></li>
<li><a title="Categoría Mejoras para el hogar" href="/category/home-improvement">Mejoras para el hogar</a></li>
<li><a title="Categoría Humor" href="/category/humor">Humor</a></li>
<li><a title="Categoría Niños y Adolescentes" href="/category/kids-teens">Niños y Adolescentes</a></li>
<li><a title="Categoría Legal" href="/category/legal">Legal</a></li>
<li><a title="Categoría Marketing" href="/category/marketing">Marketing</a></li>
<li><a title="Categoría Hombres" href="/category/men">Hombres</a></li>
<li><a title="Categoría Música y Películas" href="/category/music-and-movies">Música y Películas</a></li>
<li><a title="Categoría Negocios en línea" href="/category/online-business">Negocios en línea</a></li>
<li><a title="Categoría Crianza de los hijos" href="/category/parenting">Crianza de los hijos</a></li>
<li><a title="Categoría Animales y mascotas" href="/category/pets-and-animals">Animales y mascotas</a></li>
<li><a title="Categoría Política y Gobierno" href="/category/politics-and-government">Política y Gobierno</a></li>
<li><a title="Categoría Recreación y Deporte" href="/category/recreation-sports">Recreación y Deporte</a></li>
<li><a title="Categoría Relaciones" href="/category/relationships">Relaciones</a></li>
<li><a title="Categoría Religión y Fe" href="/category/religion-and-faith">Religión y Fe</a></li>
<li><a title="Categoría Motivación Personal" href="/category/self-improvement">Motivación Personal</a></li>
<li><a title="Categoría Promoción del sitio" href="/category/site-promotion">Promoción del sitio</a></li>
<li><a title="Categoría Viajes y Ocio" href="/category/travel-leisure">Viajes y Ocio</a></li>
<li><a title="Categoría Desarrollo Web" href="/category/web-development">Desarrollo Web</a></li>
<li><a title="Categoría Las mujeres" href="/category/women">Las mujeres</a></li>
<li><a title="Categoría Redacción" href="/category/writing">Redacción</a></li>
</ul>
</div>