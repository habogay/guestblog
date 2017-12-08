package articles.craw.controller;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.Craw;
import articles.service.CrawYoo;

@SuppressWarnings("serial")
public class YooServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		String name = req.getParameter("name");
//		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
//		Query query = psm.newQuery(Craw.class);
//		query.setRange(0,10);
//		@SuppressWarnings("unchecked")
//		List<Craw> list = (List<Craw>) query.execute();
//		for(int i=0;i<list.size();i++)
//		{
//			psm.deletePersistent(list.get(i));
//		}
		new CrawYoo(name);
	}
}
