package articles.service;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Demo {
	public Demo(String name_domain)
	{
		String message = null;
		try {
			message = name_domain.replaceAll("[<]+", "%3c");
			message = URLEncoder.encode(message.toString(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		System.out.println(message);
        try {
            URL url = new URL("http://www.microsofttranslator.com/m/default.aspx");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded "); 
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("SourceTextbox="+message);
            writer.write("&ddFromLanguage=8");
            writer.write("&ddToLanguage=3");
            writer.close();
    
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            	 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            	 String content = "";
            	 while(1==1)
     			 {
     				 String str = reader.readLine();
     				 if(str==null) break;
     				 content+=str;   				
     			 }
            	 Document doc = Jsoup.parse(content);

            	 int begin = doc.html().indexOf("<input name=\"TranslateNowCommand\" type=\"submit\" value=\" &gt; \" />");
            	 begin += "<input name=\"TranslateNowCommand\" type=\"submit\" value=\" &gt; \" />".length();
            	 int end = doc.html().indexOf("Please select your source language");
    		 	 System.out.println(doc.html().substring(begin, end));

            		 
            } else {
                // Server returned HTTP error code.
            }
        } catch (MalformedURLException e) {
            // ...
        } catch (IOException e) {
            // ...
        }

	}
	public static void main( String args[])
	{
		new Demo("<p><br/><a href='abc'>abc</a>cde Hybrid cars could be an <br/>alternative <p>which could help reduce the</p> impact that cars have on the environment. A hybrid car has an efficient internal combustion engine, which charges a set of batteries used to drive the car in slow traffic. Hybrid cars are constructed from lighter materials such as aluminum, and the combination of these designs leads to economic fuel consumption.Nowadays many car manufacturers produce hybrid cars known collectively as Hybrid Electric Vehicles (HEV). All are very efficient and although they are more expensive than a conventional car, they have proved to be of great benefit to the environment due to their reduced emissions into the atmosphere.A hybrid car is an environmentally friendly car which is propelled by a finely tuned gasoline or diesel engine in combination with an electric drive which is used in city or slow moving traffic.Various innovative technologies contribute to the hybrid car's efficiency. The high efficiency engines are constructed from an alloy which makes it very light, and it’s also very efficient due to being finely tuned and controlled by computerised monitoring systems. Tailpipe emissions, which are extremely unpleasant in many cities, are drastically reduced by the battery operation in traffic.Lighter materials mean a lighter car, and less energy is required to drive the wheels. The automatic engine stop / start component automatically cuts out the engine when you stop the car in traffic, restarting in the electric mode when the accelerator is pressed.Hybrid cars help the environment in many ways. City smog contains numerous pollutants, mainly from our car tailpipe emissions, particularly when the car engine is idling in traffic. The main component is ground level ozone, which is formed by car exhaust emissions of carbon monoxide, nitrogen dioxide, sulphur dioxide and micro dust particulates. The sun’s rays act on these noxious gasses and produce the ozone, which is particularly dangerous to young children, people with respiratory problems such as asthma, and the elderly. Ground level ozone can also transfer to rural areas where it damages agriculture and other vegetation.Hybrid cars drastically reduce the exhaust emissions which cause the low level ozone in our cities, by up to 90% less emissions than from conventional cars. They achieve this by the almost exclusive use of the electric drive motor in city driving, which emits no noxious fumes.Furthermore, the combination of all the innovative efficient energy saving components integrated into a modern hybrid car results in using less fuel than a conventional car. This leads to a higher MPG, saving energy and producing/releasing less polluting exhaust emissions into the atmosphere.About 30% of all CO2 emissions come from the use of our vehicles, which contributes to the rise in greenhouse gases. Lighter hybrid cars can reduce these greenhouse gas emissions through the use of an efficient engine coupled with the ability to switch to electric drive in heavy traffic. The benefits are obvious, so the next time you need to replace your car windshield, you may also want to consider the switch to a lighter hybrid car.</p> ");
	}
}
