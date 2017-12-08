package articles.craw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.service.CrawContentYoo;

@SuppressWarnings("serial")
public class YooContentServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		int start = Integer.parseInt(req.getParameter("start"));
		new CrawContentYoo(start);
	}
}
