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
public class PostEditCategoryAdminServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
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
		
		String title_url = req.getParameter("old_alias");
		Query query_detail = psm.newQuery(Category.class);
		query_detail.setFilter("alias=='"+title_url+"'");
		@SuppressWarnings("unchecked")
		List<Category> category = (List<Category>) query_detail.execute();
		
		if(category.size() > 0)
		{
			Category update = new Category();
			
			update.setName(req.getParameter("name"));
			update.setTitle(req.getParameter("title"));
			update.setKeyword(req.getParameter("keyword"));
			Text description = new Text(req.getParameter("description"));
			update.setDescription(description);
			int order = Integer.parseInt(req.getParameter("order"));
			update.setOrder(order);
			
			@SuppressWarnings("static-access")
			String replace = req.getParameter("alias").trim();		
			update.setAlias(replace);
			update.setKey(category.get(0).getKey());
			
			Query query = psm.newQuery(Category.class);
			query.setFilter("alias=='"+replace+"' && key != keyParam");
			query.declareParameters("com.google.appengine.api.datastore.Key keyParam"); 
			@SuppressWarnings("unchecked")
			List<Category> check = (List<Category>) query.execute(category.get(0).getKey());
			if(check.size() > 0)
			{
				resp.getWriter().println("Category already exists");
			} else {
				psm.makePersistent(update);
				resp.getWriter().println("Update success.<br/><a href='/listcategory'>preview</a>");
			}
		}
	}

}
