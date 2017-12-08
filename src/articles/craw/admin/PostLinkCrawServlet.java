package articles.craw.admin;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.LinkCraw;

@SuppressWarnings("serial")
public class PostLinkCrawServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		LinkCraw insert = new LinkCraw();
		
		insert.setLink(req.getParameter("link"));
		insert.setAliasCategory(req.getParameter("aliasCategory"));
		insert.setStart(Integer.parseInt(req.getParameter("start")));
		insert.setEnd(Integer.parseInt(req.getParameter("end")));
		insert.setDomain(req.getParameter("domain"));
		insert.setActive("1");
		
		psm.makePersistent(insert);
		
		resp.getWriter().println("Add success.<br/><a href='/postlinkcraw'>Add Link craw</a>");
	}

}
