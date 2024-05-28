package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	
	public static RequestSpecification reqSpec;
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if(reqSpec==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
			.addQueryParam("key", "qaclick123")
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON).build();
		
		return reqSpec;
		}
		return reqSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Abhay\\eclipse-workspace-RestAssured\\APIFramework\\src\\test\\java\\resources\\Global.properties");
		prop.load(fis);
		return prop.getProperty(key);		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String re = response.asString();
		JsonPath js = new JsonPath(re);
		String s= js.get(key).toString();
		return s;
	}

}
