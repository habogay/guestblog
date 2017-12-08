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
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SendemailServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String strCallResult = "";
		resp.setContentType("text/plain");
		try {
			// Extract out the To, Subject and Body of the Email to be sent
			String strTo = req.getParameter("email");
			String name_email = req.getParameter("name_email");
			String strSubject = req.getParameter("subject");
			String strBody = req.getParameter("content");

			// Do validations here. Only basic ones i.e. cannot be null/empty
			// Currently only checking the To Email field
			if (strTo == null)
				throw new Exception("To field cannot be empty.");

			// Trim the stuff
			strTo = strTo.trim();
			if (strTo.length() == 0)
				throw new Exception("To field cannot be empty.");

			// Call the GAEJ Email Service
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			
			MimeMessage  msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(req.getParameter("from_email"),"Hatforrent.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					strTo));
			
			msg.setContent(strBody, "text/html");
			msg.setSubject(strSubject, "UTF-8");
			Transport.send(msg);
			strCallResult = "Success: " + "Email has been delivered.";
			resp.getWriter().println(strCallResult);
		} catch (Exception ex) {
			strCallResult = "Fail: " + ex.getMessage();
			resp.getWriter().println(strCallResult);
		}
	}
}
