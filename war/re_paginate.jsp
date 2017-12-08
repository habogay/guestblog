<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%
	int check_paginate;
	int cur_page = Integer.parseInt((String)request.getParameter("cur_page"));
	int number_game = Integer.parseInt((String)request.getParameter("number_game"));
	String url = request.getParameter("url");
	int limit = 100;
	
	if(cur_page == 1 && number_game < limit)
	{
		check_paginate = 1;
	} else if(cur_page > 1 && number_game < limit){
		check_paginate = 2;
	} else {
		check_paginate = 3;
	}

	if(check_paginate == 2)
	{
		int show_page = 0;
		if(cur_page > 1)
		{
			out.print("<a href=\"/"+url+"/"+(cur_page - 1)+"\">Prev</a>");
		}
	
		if(cur_page < 11)
		{
			for(int i=0;i<cur_page;i++)
			{
				if(cur_page == (i+1))
				{
					out.print("<span>"+(i+1)+"</span>");
				} else {
					out.print("<a href=\"/"+url+"/"+(i+1)+"\">"+(i+1)+"</a>");
				}
			}
		} else {
			for(int i=(cur_page-11);i<cur_page;i++)
			{
				if(cur_page == (i+1))
				{
					out.print("<span>"+(i+1)+"</span>");
				} else {
					out.print("<a href=\"/"+url+"/"+(i+1)+"\">"+(i+1)+"</a>");
				}
			}
		}
	}
	if(check_paginate == 3)
	{
		int show_page = 0;
		if(cur_page > 1)
		{
			out.print("<a href=\"/"+url+"/"+(cur_page - 1)+"\">Prev</a>");
		}
	
		if(cur_page > 5)
		{
			show_page = cur_page - 6;
		} 
		for(int i=show_page;i<=show_page+10;i++)
		{
			if(cur_page == (i+1))
			{
				out.print("<span>"+(i+1)+"</span>");
			} else {
				out.print("<a href=\"/"+url+"/"+(i+1)+"\">"+(i+1)+"</a>");
			}
		}
%>
		<a href="/<%=url+"/"+(cur_page + 1) %>">Next</a>
<%
	}
%>