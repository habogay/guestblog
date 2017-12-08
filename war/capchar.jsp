<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>

    <%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>


    <html>
      <body>
		<script>
        	var RecaptchaOptions = { theme : 'clean' };
  		</script>
        <form action="/postcapchar" method="post">
        <%
          ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LdXmcISAAAAAHz6FsABghup-EayldUCiKQyDPAk", "6LdXmcISAAAAANPIv5ykGg3LAlBMnUQHzy3-Kiqo", false);
          out.print(c.createRecaptchaHtml(null, null));
        %>
        <input type="submit" value="submit" />
        </form>
      </body>
    </html>

