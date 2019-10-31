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

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Async
public class POSTRequestClient {
    public String webLink = null;
    public JSONObject responseBody = new JSONObject();
    private String bearerToken;

    //Class Constructor.
    public POSTRequestClient(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException
        { POSTMethod(credentialsObject, transactionObject, false, null); }

    // Create a Http object for making POST request to Xente API.
    JSONObject POSTMethod(JSONObject credentials, JSONObject transaction, boolean newToken, String link) throws IOException, JSONException {
        //Create local variables to be used.
        objectHandler objectHandler = new objectHandler(credentials, transaction);
        tokenHandler tokenHandler = new tokenHandler(credentials, transaction);
        JSONObject object = new JSONObject();
        link = webLink;

        //Determine whether bearerToken is available or not
        if(newToken = true || bearerToken.isEmpty()){
            tokenHandler.createToken(credentials, transaction);
            bearerToken = tokenHandler.bearerToken;
        }
        else
            { bearerToken = tokenHandler.bearerToken; }

        //POST data to the Xente API.
        try {
            //Create object for the Header section.
            object.put("Content-Type", "application/json");
            object.put("X-ApiAuth-ApiKey", objectHandler.apiKey);
            object.put("X-Date", new Date().toString());
            object.put("X-Correlation-ID", new Date().getTime());
            object.put("Authorization", bearerToken);

            //Declare the URL to be used.
            URL url = new URL(link);

            //Create Object to interact with Xente API.
            Map<String, Object> httpOptions = new LinkedHashMap<>();
            httpOptions.put("method", "POST");
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

            //Establish a connection with the Xente API.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);

            //Receive the response body from the Xente API.
            Reader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = input.read()) >= 0;)
                sb.append((char)c);
            String response = sb.toString();
            responseBody = new JSONObject(response);
            System.out.println("Result after reading JSON Response Body");
            System.out.println(responseBody);
        }
        //Catch & handle errors here.
        catch (JSONException | IOException e) {
            if(e.getMessage().equals(String.valueOf(401)))  {
                tokenHandler = new tokenHandler(credentials, transaction);
                tokenHandler.createToken(credentials, transaction);
                bearerToken = tokenHandler.bearerToken;
                POSTMethod(credentials, transaction, false, null);
            }
            else
                { e.printStackTrace(); }
        }

        //Return the response body
        return responseBody;
    }
}