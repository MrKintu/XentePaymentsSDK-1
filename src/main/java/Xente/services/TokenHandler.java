/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente.services;

import com.squareup.okhttp.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Async
public class TokenHandler {
    //Declare the variables to be accessed globally.
    public String bearerToken;

    //Initiate Class Constructor
    public TokenHandler(JSONObject credentialsObject, JSONObject transactionObject) throws IOException
        { createToken(credentialsObject, transactionObject); }

    // Create a Http object for making request
    String createToken(JSONObject credentials, JSONObject transaction) throws IOException {
        //Create local variables to be used within the method.
        ObjectHandler objectHandler = new ObjectHandler(credentials, transaction);
        URLConstants urlconstants = new URLConstants(credentials, transaction);

        //Create custom date format for Xente API.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        //Build header section to be sent to the Xente API.
        Headers.Builder builder = new Headers.Builder();
        builder.add("X-ApiAuth-ApiKey", objectHandler.apiKey);
        builder.add("X-Date", simpleDateFormat.format(new Date()));
        builder.add("X-Correlation-ID", String.valueOf(new Date().getTime()));

        //Perform POST Method to Xente API.
        OkHttpClient client = new OkHttpClient();
        Request requestBody = new Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), credentials.toString()))
                .url(urlconstants.authURL).headers(builder.build()).build();

        //Collect response body from Xente API and assign bearer token value to variable.
        Response response = client.newCall(requestBody).execute();
        if(response != null){
            if(response.isSuccessful()) {
                try {
                    String body = response.body().string();
                    System.out.println(body + "\ncode: " + response.code());
                    JSONObject responseBody = new JSONObject(body);
                    bearerToken = responseBody.getString("token");
                    return bearerToken;
                }
                catch (JSONException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            else
                { return null; }
        }
        else
            { return null; }
    }
}
