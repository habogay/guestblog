package articles.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GoogleSearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		//seo
		req.setAttribute("seo_title", "Búsqueda de artículos");
		req.setAttribute("seo_description", "Si usted está interesado en una alternativa de alto valor a la compra de un coche nuevo, un auto usado puede ser la mejor opción para usted.Coches usados tienen una tasa estabilizada de depreciacción que.");
		req.setAttribute("title", "Búsqueda de artículos");
		req.setAttribute("description", "Búsqueda de artículos articles.Free proporcionada por yooarticles.com - el directorio de artículos libres. Buscar artículos en línea gratis para tu sitio web, publicación electrónica o boletines informativos. Envíe sus artículos para la distribución gratuita y la publicación.");
		req.setAttribute("keyword", "yooarticles.com,presente artículo libre, en el artículo, los artículos");
		
		try {
			req.getRequestDispatcher("/googlesearch.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
