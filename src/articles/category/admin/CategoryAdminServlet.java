package articles.category.admin;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.data.QnAPersistenceManager;
import articles.model.Users;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")

public class CategoryAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user != null) {
        	Query query = psm.newQuery(Users.class);
        	query.setFilter("username == '"+user.getNickname()+"'");
        	@SuppressWarnings("unchecked")
			List<Users> users = (List<Users>) query.execute();
        	if(users.size() <= 0)
        	{
        		resp.sendRedirect("/");
        	}
        	
        } else {
            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
		
		try {
			req.getRequestDispatcher("admin_category/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
