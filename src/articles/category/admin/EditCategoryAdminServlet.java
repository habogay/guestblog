package articles.category.admin;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

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

public class EditCategoryAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
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
		
		String path = ((HttpServletRequest)req).getRequestURI();
		StringTokenizer st = new StringTokenizer( path,"/");
        int count = st.countTokens(); 

        if(count!=2)
        {
        	resp.getWriter().println("Bad request : "+req.getRequestURI());
        	resp.getWriter().close();
        	return ;
        }

		// skip one token /question/abcd (remove sites)
		st.nextToken();
		String title_url = st.nextToken();
		
		Query query_category = psm.newQuery(Category.class);
		query_category.setFilter("alias=='"+title_url+"'");
		@SuppressWarnings("unchecked")
		List<Category> category = (List<Category>) query_category.execute();
		if(category.size()>0)
		{
			req.setAttribute("category", category);
			
			try {
				req.getRequestDispatcher("/admin_category/edit.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
