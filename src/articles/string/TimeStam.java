package articles.string;

import java.util.Date;

public class TimeStam {
	public static int time() {
		int time = 0;
	    Date date  = new Date();
	    
	    time = (date.getYear() - 2011)*365*24*60*60 + (date.getMonth() - 1)*30*24*60*60 + (date.getDay() - 1)*24*60*60 + date.getHours()*60 + date.getSeconds();
	    
	    return time;
    }
}
