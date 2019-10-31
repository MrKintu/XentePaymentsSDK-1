/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import XenteJavaSDK.objectBodies.objectHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Async
public class GETRequestClient {
    public JSONObject responseBody;

    //Class Constructor.
    public GETRequestClient(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException
        { GETMethod(credentialsObject, transactionObject, null); }

    // Create a Http object for making GET HTTP request to Xente API.
    //It takes in the credentials object, the transaction object, boolean value on whether a new token is needed
    //and the respective URL link as parameters.
    public JSONObject GETMethod(JSONObject credentials, JSONObject transaction, String webLink) throws IOException, JSONException {
        //Create local variables to be used.
        objectHandler objectHandler = new objectHandler(credentials, transaction);
        tokenHandler tokenHandler = new tokenHandler(credentials, transaction);
        JSONObject object = new JSONObject();
        String bearerToken = tokenHandler.bearerToken;

        //Determine whether bearerToken is available or not
        if(bearerToken.isEmpty()) {
            tokenHandler.createToken(credentials, transaction);
            bearerToken = tokenHandler.bearerToken;
        }
        else
            { bearerToken = tokenHandler.bearerToken; }

        //GET Data from Xente API.
        try {
            //Create object for the Header section.
            object.put("Content-Type", "application/json");
            object.put("X-ApiAuth-ApiKey", objectHandler.apiKey);
            object.put("X-Date", new Date().toString());
            object.put("X-Correlation-ID", new Date().getTime());
            object.put("Authorization", bearerToken);

            //Create Object to interact with Xente API.
            Map<String, Object> httpOptions = new LinkedHashMap<>();
            httpOptions.put("method", "GET");
            httpOptions.put("headers", object);
            httpOptions.put("body", String.valueOf(transaction));
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> params : httpOptions.entrySet()){
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(params.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(params.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

            //Declare the URL to be used.
            URL url = new URL(webLink);

            //Establish connection too Xente API.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);
            int responseCode = connection.getResponseCode();

            //Handle 401 Unauthorised error.
            if(responseCode == 401) {
                tokenHandler.createToken(credentials, transaction);
                GETMethod(credentials, transaction, webLink);
            }

            //Receive the response body from the Xente API.
            System.out.println("Sending 'GET' request to URL : " + webLink);
            System.out.println("\nResponse Code : " + responseCode);
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = input.readLine()) != null) {
                sb.append(inputLine);
            }
            input.close();

            //Display the response body and assign it to variable.
            responseBody = new JSONObject(sb.toString());
            System.out.println(responseBody);
        }
        catch (JSONException | IOException e)
            { e.printStackTrace(); }

        //Return the response body.
        return responseBody;
    }
}
