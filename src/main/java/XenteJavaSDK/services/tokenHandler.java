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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


@Async
public class tokenHandler {
    //Declare the variables to be used.
    public String bearerToken;

    //Initiate Class Constructor
    public tokenHandler(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException
        { createToken(credentialsObject, transactionObject); }

    // Create a Http object for making request
    String createToken(JSONObject credentials, JSONObject transaction) throws IOException, JSONException {
        //Create local variables to be used within the method.
        objectHandler objectHandler = new objectHandler(credentials, transaction);
        constant constant = new constant(credentials, transaction);

        //Generate Token in the try clause
        try {
            //Create object for the Header section.
            JSONObject object = new JSONObject();
            object.put("Content-Type", "application/json");
            object.put("X-ApiAuth-ApiKey", objectHandler.apiKey);
            object.put("X-Date", new Date().toString());
            object.put("X-Correlation-ID", new Date().getTime());
            object.put("Authorization", "null");

            //Create Object to send to Xente API
            Map<String, Object> httpOptions = new LinkedHashMap<>();
            httpOptions.put("method", "POST");
            httpOptions.put("headers", object);
            httpOptions.put("body", String.valueOf(credentials));
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> params : httpOptions.entrySet()){
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(params.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(params.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

            //Declare URL to be used.
            URL url = new URL(constant.authURL);

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
            JSONObject responseBody = new JSONObject(response);

            //Read the Response Body and assign the token value to the local variable.
            System.out.println("Result JSON Response Body for request from Xente");
            JSONObject form_data = responseBody.getJSONObject("form");
            bearerToken = form_data.getString("token");
            System.out.println("Token: "+form_data.getString("token"));
            System.out.println("Message: "+form_data.getString("message"));
        }
        //Catch all errors here and display them.
        catch (JSONException | MalformedURLException e) {
            Reader input = new BufferedReader(new StringReader(e.getMessage()));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = input.read()) >= 0;)
                sb.append((char)c);
            String errors = sb.toString();
            JSONObject errorBody = new JSONObject(errors);

            if(errorBody.getString("status").equals("Unauthorized")) {
                System.out.println("Incorrect credentials. Please check either API Key, Password or Mode.");
            }
            else
                { e.printStackTrace(); }
        }

        //Return the bearerToken
        return bearerToken;
    }
}
