<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%
String title = "Privacy Policy";
String description = "Free articles provided by es.yooarticles.com - your free articles directory. Find free online articles for your website, eZine or newsletters. Submit your Articles for free syndication and publication.";
String keywords = "es.yooarticles.com,submit free article,article,articles";
%>
<jsp:include page="layout/header.jsp">
	<jsp:param name="title" value="<%=title %>"/>
	<jsp:param name="description" value="<%=description %>"/>
	<jsp:param name="keywords" value="<%=keywords %>"/>
</jsp:include>

		<div class="content">
			
			<jsp:include page="element/left.jsp"></jsp:include>
			
			<div class="show" style="line-height: 30px;">
				<h1>Privacy Policy</h1>
				<jsp:include page="element/adv_one.jsp"></jsp:include>
				<hr size="1"/>
				<p><b> Privacy Policy for es.yooarticles.com </b></p> 
					<p> If you require any more information or have any questions about our privacy policy, please feel free to contact us by email at buidinhngoc.aiti@gmail.com. </p> 
					<p> At es.yooarticles.com, the privacy of our visitors is of extreme importance to us. This privacy policy document outlines the types of personal information is received and collected by es.yooarticles.com and how it is used. </p> 
					<p> <b>Log Files</b><br> Like many other Web sites, es.yooarticles.com makes use of log files. The information inside the log files includes internet protocol ( IP ) addresses, type of browser, Internet Service Provider ( ISP ), date/time stamp, referring/exit pages, and number of clicks to analyze trends, administer the site, track user's movement around the site, and gather demographic information. IP addresses, and other such information are not linked to any information that is personally identifiable. </p> 
					<p> <b>Cookies and Web Beacons</b><br> es.yooarticles.com does not use cookies. </p> 
					<b>DoubleClick DART Cookie</b><br> 
					<p> 
					.:: Google, as a third party vendor, uses cookies to serve ads on es.yooarticles.com.<br> 
					.:: Google's use of the DART cookie enables it to serve ads to your users based on their visit to es.yooarticles.com and other sites on the Internet. <br> 
					.:: Users may opt out of the use of the DART cookie by visiting the Google ad and content network privacy policy at the following URL - http://www.google.com/privacy_ads.html </p> 
					<p> Some of our advertising partners may use cookies and web beacons on our site. Our advertising partners include ....... <br> Google Adsense
					<br> <br>  </p> 
					<p> These third-party ad servers or ad networks use technology to the advertisements and links that appear on es.yooarticles.com send directly to your browsers. They automatically receive your IP address when this occurs. Other technologies ( such as cookies, JavaScript, or Web Beacons ) may also be used by the third-party ad networks to measure the effectiveness of their advertisements and / or to personalize the advertising content that you see. </p> 
					<p> es.yooarticles.com has no access to or control over these cookies that are used by third-party advertisers. </p> 
					<p> You should consult the respective privacy policies of these third-party ad servers for more detailed information on their practices as well as for instructions about how to opt-out of certain practices. es.yooarticles.com's privacy policy does not apply to, and we cannot control the activities of, such other advertisers or web sites. </p> 
					<p> If you wish to disable cookies, you may do so through your individual browser options. More detailed information about cookie management with specific web browsers can be found at the browsers' respective websites. </p>
			</div>
			<div class="related">
				<ul class="show_t_r">
					<%
						String alias = "";
					%>
					
					<jsp:include page="element/new_tag.jsp"></jsp:include>
					
					<jsp:include page="element/top_view.jsp">
						<jsp:param name="alias" value="<%=alias %>"/>
					</jsp:include>
					<jsp:include page="element/top_vote.jsp">
						<jsp:param name="alias" value="<%=alias %>"/>
					</jsp:include>
				</ul>
			</div>
			<div class="clear"></div>
		</div>

<%@ include file='/layout/footer.jsp'%>