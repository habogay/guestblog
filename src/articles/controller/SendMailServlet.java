package articles.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SendMailServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		  Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);

	        String name = req.getParameter("name");
	        String email = req.getParameter("email");
	        String text = req.getParameter("message");
	        
	        String msgBody = text;

	        try {
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress("admin@showsiteinfo.com",email));
	            msg.addRecipient(Message.RecipientType.TO,
	                             new InternetAddress("admin@showsiteinfo.com",email));
	            msg.setSubject("Mail contact from "+name);
	            msg.setText(msgBody);
	            Transport.send(msg);
	           
	        } catch (AddressException e) {
	            // ...
	        } catch (MessagingException e) {
	            // ...
	        }
	        resp.sendRedirect("/contact");
	}

}