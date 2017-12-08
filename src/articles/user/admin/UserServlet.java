package articles.user.admin;

import articles.data.QnAPersistenceManager;
import articles.model.Users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("Hello, world");
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user != null) {
        	PersistenceManager psm = QnAPersistenceManager.get().getPersistenceManager();
        	
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
			req.getRequestDispatcher("/user/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
