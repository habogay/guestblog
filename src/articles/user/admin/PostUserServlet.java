package articles.user.admin;

import articles.data.QnAPersistenceManager;
import articles.model.Users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class PostUserServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
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
		
		Users user2 = new Users();
		user2.setUsername(req.getParameter("username"));
		
		int group = Integer.parseInt(req.getParameter("group"));
		
		user2.setGroup(group);
		user2.setActive(1);
		
		psm.makePersistent(user2);
		resp.getWriter().println("Add success<br/><a href='/listgame'>List game</a> | <a href='/user'>Add user</a>");
	}
}
