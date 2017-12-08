package articles.string;

import java.util.regex.Pattern;

public class Replace {
	public static String replace(String str) {
	    str = str.replaceAll("[\\W]+", "-");
	    str = str.replaceAll("^-","");
	    str = str.replaceAll("-$","");
	    str = str.toLowerCase();
	    return str;
    }
	public static String remove(String str) {
	    str = str.replaceAll("[\'\"]+", "&quot;");
	    return str;
    }
	public static String keyword(String str,String remove)
	{
		String string  = "";
		str = str.replaceAll("[ ]*[.]+[ ]*", "daucham");
		str = str.replaceAll("[\\W]+", "-");
		str = str.replaceAll("daucham", " .");
		String[] words = str.split("-");
		int count = 0;
		String check = "";
		String[] check_arr = new String[5];
		String s_word = "";
		String s_check = "";
		for(int i=0;i<(words.length-1);i++)
		{
			if(Pattern.matches("^[A-Z]+[\\w\\W]+", words[i]) && count <=4 && words[i].length()>4 && i != 0 && !remove.equals(words[i]))
			{
				check = "";
				s_word = words[i].toLowerCase();
				for(int j=0;j<check_arr.length;j++)
				{
					if(check_arr[j] != null)
						s_check = check_arr[j].toLowerCase();
					if(s_word.equals(s_check))
					{
						check = "1";
					}
				}
				if(check.equals(""))
				{
					if(count == 0)
					{
						if(words[i+1] != null && words[i+1].length()>5 && Pattern.matches("^[^.]+[\\w\\W]+", words[i]))
							string += words[i].replaceAll("[ .]+", " ")+" "+words[i+1].replaceAll("[ .]+", " ");
						else
							string += words[i].replaceAll("[ .]+", " ");
					} else {
						if(words[i+1] != null && words[i+1].length()>5 && Pattern.matches("^[^.]+[\\w\\W]+", words[i]))
							string += ","+words[i].replaceAll("[ .]+", " ")+" "+words[i+1].replaceAll("[ .]+", " ");
						else
							string += ","+words[i].replaceAll("[ .]+", " ");
					}
					check_arr[count] = words[i];
					count++;
				}
			}
			if(count >=4)
			{
				break;
			}
		}
		
		return string;
	}
}
