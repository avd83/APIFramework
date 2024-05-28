package stepDefinations;

import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		stepDefination sd = new stepDefination();
		if(stepDefination.place_id==null)
		{
		sd.add_place_payload_with("ashish", "marwadi", "pimpri");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_Id_created_maps_to_using("ashish", "GetPlaceAPI");
		}
	}

}
