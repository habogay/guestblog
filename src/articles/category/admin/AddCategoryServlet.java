package articles.category.admin;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import articles.data.QnAPersistenceManager;
import articles.model.Category;
import articles.model.Users;
import articles.string.Replace;

@SuppressWarnings("serial")
public class AddCategoryServlet extends HttpServlet {
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
		
		Category category = new Category();
		
		category.setName(req.getParameter("name"));
		category.setTitle(req.getParameter("title"));
		category.setKeyword(req.getParameter("keyword"));
		
		Text description = new Text(req.getParameter("description"));
		category.setDescription(description);
		
		int order = Integer.parseInt(req.getParameter("order"));
		category.setOrder(order);
		
		@SuppressWarnings("static-access")
		String replace = req.getParameter("alias");
		
		category.setAlias(replace);
		
		Query query = psm.newQuery(Category.class);
		query.setFilter("alias=='"+replace+"'");
		
		@SuppressWarnings("unchecked")
		List<Category> check = (List<Category>) query.execute();
		
		if(check.size() > 0)
		{
			resp.getWriter().println("Name cateogry already exists");
		} else {
			psm.makePersistent(category);
			resp.getWriter().println("Add success.<br/><a href='/postcategory'>Add category</a>");
		}
	}
}
