package ScreenshotTestingAPI.MultiURL_ScreenshotTesting;

import java.io.IOException;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScreenshotAPI  {
	       
	
@SuppressWarnings("unchecked")
public static void main(String args[])
{
	
		String username = "ramitdlambdatest"; // username
		String accesskey = "Ar7sr82ACbQXRi23ujktWaSuBRq9jOjInvBaelieyC00XavZUP"; // accesskey
		String usernameColonPassword = username + ":" + accesskey;
		String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes()); // Encoding

		String[] MultiURL = { "https://www.lambdatest.com", "https://www.apple.com","https://www.nike.com"  }; // List of URL's

		for (int i = 0; i < MultiURL.length; i++) {
			
			// JSON Schema
			JSONObject jo = new JSONObject();
			jo.put("url", MultiURL[i]);
			jo.put("defer_time", new Integer(5));
			jo.put("email", true);
			jo.put("mac_res", "1024x768");
			jo.put("win_res", "1366X768");
			jo.put("smart_scroll", true);
			jo.put("layout", "portrait");

			JSONObject config = new JSONObject();
			jo.put("configs", config);

			JSONObject Windows10 = new JSONObject();  //WIN10
			config.put("windows 10", Windows10);

			JSONArray chromebrowser = new JSONArray();  //chrome
			chromebrowser.add("76");
			chromebrowser.add("75");
			
			JSONArray firefoxbrowser = new JSONArray();  //firefox
			firefoxbrowser.add("65");
			firefoxbrowser.add("66");

			Windows10.put("chrome", chromebrowser);
			Windows10.put("firefox", firefoxbrowser);
			
			JSONObject mac_mojave = new JSONObject();  //WIN10
			config.put("macos mojave", mac_mojave);
			
			JSONArray safaribrowser = new JSONArray();  //safari
			safaribrowser.add("12");
			
			mac_mojave.put("chrome", chromebrowser);
			mac_mojave.put("firefox", firefoxbrowser);
			mac_mojave.put("safari", safaribrowser);
			
			
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");  //JSON File
		RequestBody body = RequestBody.create(mediaType, jo.toJSONString());
		Request request = new Request.Builder()
		  .url("https://api.lambdatest.com/screenshots/v1/")	//endpoint URL
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", basicAuthPayload)
		  .addHeader("Accept", "*/*")
		  .addHeader("Host", "api.lambdatest.com")
		  .addHeader("Accept-Encoding", "gzip, deflate")
		  .addHeader("Connection", "keep-alive")
		  .build();

			Response response = null;

			try {
				response = client.newCall(request).execute();
				System.out.println("=============");
				System.out.println(response);
				String response_body = response.body().string();
				System.out.println(response_body); // API Response
				System.out.println("=============");

				if (response.isSuccessful()) {

					System.out.println("Success Response");
				} else {
					throw new IOException("Unexpected HTTP code " + response);
				}
			}

			catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
