<%@page import="articles.string.Replace"%>
<%@page import="java.util.List"%>
<%@page import="articles.model.Category"%>
<%@page import="articles.data.QnAPersistenceManager"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="javax.jdo.Query"%>
<div class="list">
	<ul class="child_list">
		<li class="one">Sitio men�</li>
		<li><a href="/" title="Casa">Casa</a></li>
		<li><a href="/search" title="B�squeda en Google">B�squeda</a></li>
		<li><a href="/archives" title="Archives">Archivos</a></li>
		<li><a href="/submit-articles" title="Enviar un art�culo">Enviar un art�culo</a></li>
		<li><a href="/rss" title="Los �ltimos art�culos rss">Los �ltimos art�culos (RSS)</a></li>
	</ul>
	<ul class="child_list">
		<li class="one">Categor�a</li>
		<li><a title="Categor�a Auto y Camiones" href="/category/auto-trucks">Auto y Camiones</a></li>
<li><a title="Categor�a Negocios y Finanzas" href="/category/business-finance">Negocios y Finanzas</a></li>
<li><a title="Categor�a Computadoras e Internet" href="/category/computers-internet">Computadoras e Internet</a></li>
<li><a title="Categor�a Educaci�n" href="/category/education">Educaci�n</a></li>
<li><a title="Categor�a Medio Ambiente y Going Green" href="/category/environment-and-going-green">Medio Ambiente y Going Green</a></li>
<li><a title="Categor�a Familia" href="/category/family">Familia</a></li>
<li><a title="Categor�a Comida y bebida" href="/category/food-drink">Comida y bebida</a></li>
<li><a title="Categor�a Admin�culos y trastos" href="/category/gadgets-and-gizmos">Admin�culos y trastos</a></li>
<li><a title="Categor�a Salud" href="/category/health">Salud</a></li>
<li><a title="Categor�a Aficiones" href="/category/hobbies">Aficiones</a></li>
<li><a title="Categor�a Mejoras para el hogar" href="/category/home-improvement">Mejoras para el hogar</a></li>
<li><a title="Categor�a Humor" href="/category/humor">Humor</a></li>
<li><a title="Categor�a Ni�os y Adolescentes" href="/category/kids-teens">Ni�os y Adolescentes</a></li>
<li><a title="Categor�a Legal" href="/category/legal">Legal</a></li>
<li><a title="Categor�a Marketing" href="/category/marketing">Marketing</a></li>
<li><a title="Categor�a Hombres" href="/category/men">Hombres</a></li>
<li><a title="Categor�a M�sica y Pel�culas" href="/category/music-and-movies">M�sica y Pel�culas</a></li>
<li><a title="Categor�a Negocios en l�nea" href="/category/online-business">Negocios en l�nea</a></li>
<li><a title="Categor�a Crianza de los hijos" href="/category/parenting">Crianza de los hijos</a></li>
<li><a title="Categor�a Animales y mascotas" href="/category/pets-and-animals">Animales y mascotas</a></li>
<li><a title="Categor�a Pol�tica y Gobierno" href="/category/politics-and-government">Pol�tica y Gobierno</a></li>
<li><a title="Categor�a Recreaci�n y Deporte" href="/category/recreation-sports">Recreaci�n y Deporte</a></li>
<li><a title="Categor�a Relaciones" href="/category/relationships">Relaciones</a></li>
<li><a title="Categor�a Religi�n y Fe" href="/category/religion-and-faith">Religi�n y Fe</a></li>
<li><a title="Categor�a Motivaci�n Personal" href="/category/self-improvement">Motivaci�n Personal</a></li>
<li><a title="Categor�a Promoci�n del sitio" href="/category/site-promotion">Promoci�n del sitio</a></li>
<li><a title="Categor�a Viajes y Ocio" href="/category/travel-leisure">Viajes y Ocio</a></li>
<li><a title="Categor�a Desarrollo Web" href="/category/web-development">Desarrollo Web</a></li>
<li><a title="Categor�a Las mujeres" href="/category/women">Las mujeres</a></li>
<li><a title="Categor�a Redacci�n" href="/category/writing">Redacci�n</a></li>
</ul>
</div>