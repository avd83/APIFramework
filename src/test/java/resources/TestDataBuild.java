package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.SetLocation;

public class TestDataBuild {
	
	public SetLocation addPlacePayload(String name,String language,String address)
	{
		SetLocation sl = new SetLocation();
		sl.setAccuracy(60);
		sl.setAddress(address);
		sl.setLanguage(language);
		sl.setPhone_number("(+91) 7876543456");
		sl.setWebsite("https://rahulshettyacademy.com");
		sl.setName(name);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");		
		myList.add("shop");		
		sl.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		sl.setLocation(l);
		return sl;
		
		
	}
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "\"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}

}
