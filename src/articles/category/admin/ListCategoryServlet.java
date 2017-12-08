package articles.category.admin;

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

import articles.data.QnAPersistenceManager;
import articles.model.Category;
import articles.model.Users;

@SuppressWarnings("serial")

public class ListCategoryServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("This page does not exist or has been deleted, please select a page");
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
		
		Query query = psm.newQuery(Category.class);
		@SuppressWarnings("unchecked")
		List<Category> listcategory = (List<Category>) query.execute();
		if(listcategory.size()>0)
		{
			req.setAttribute("listcategory", listcategory);
			
			try {
				req.getRequestDispatcher("/admin_category/listcategory.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
