/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * This software is open for usage and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import XenteJavaSDK.objectBodies.objectHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static com.ea.async.Async.await;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class tokenHandler {
    public String bearerToken;

    public tokenHandler(JSONObject credentialsObject) throws IOException {
        constant constant = new constant(credentialsObject);
        constant.getDomain(credentialsObject);
        createToken(credentialsObject);
    }

    // Create a Http object for making request
    @Async
    private CompletableFuture<String> createToken(JSONObject credentials) throws IOException {
        //Create local variables to be used within the method.
        XenteJavaSDK.objectBodies.objectHandler objectHandler = new objectHandler(credentials);
        constant constant = new constant(credentials);

        //Generate Token in the try clause
        try {
            //Create object for the Header section in the httpObject.
            JSONObject object = new JSONObject();
            object.put("Content-Type", "application/json");
            object.put("X-ApiAuth-ApiKey", objectHandler.apiKey);
            object.put("X-Date", new Date().toString());
            object.put("X-Correlation-ID", new Date().getTime());
            object.put("Authorization", "null");

            //Declare URL to be used.
            URL url = new URL(constant.authURL);

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

            //Set the URL Connection parameters.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);

            //Read the response body from the Xente API.
            Reader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = input.read()) >= 0;)
                sb.append((char)c);
            String response = sb.toString();
            JSONObject responseBody = new JSONObject(response.toString());

            //Assign the string value to the local variable and display the results.
            System.out.println("result after Reading JSON Response body");
            JSONObject form_data = responseBody.getJSONObject("form");
            bearerToken = form_data.getString("token");
            System.out.println("Token: "+form_data.getString("token"));
            System.out.println("Message: "+form_data.getString("message"));
        }
        //Catch all errors here and display them.
        catch (JSONException | MalformedURLException e)
            { e.printStackTrace(); }

        //Return the bearerToken
        return completedFuture(bearerToken);
    }
}
