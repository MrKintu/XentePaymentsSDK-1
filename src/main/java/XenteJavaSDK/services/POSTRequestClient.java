/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import com.squareup.okhttp.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Async
public class POSTRequestClient {
    //Declare the variables to be accessed globally.
    public JSONObject responseBody;

    //Class Constructor.
    public POSTRequestClient(JSONObject credentialsObject, JSONObject transactionObject) throws IOException {
        String link = "";
        POSTMethod(credentialsObject, transactionObject, link);
    }

    // Create a Http object for making POST HTTP request to Xente API.
    //It takes in the credentials object, the Transaction object, boolean value on whether a new token is needed
    //and the respective URL link as parameters.
    public JSONObject POSTMethod(JSONObject credentials, JSONObject transaction, String webLink) throws IOException {
        //Create local variables to be used.
        ObjectHandler objectHandler = new ObjectHandler(credentials, transaction);
        TokenHandler tokenHandler = new TokenHandler(credentials, transaction);
        String bearerToken = tokenHandler.bearerToken;

        //Determine whether bearerToken is available or not.
        if(bearerToken.isEmpty()) {
            tokenHandler.createToken(credentials, transaction);
            bearerToken = tokenHandler.bearerToken;
        }
        else
        { bearerToken = tokenHandler.bearerToken; }

        //Create custom date format for Xente API.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        //Build header section to be sent to the Xente API.
        Headers.Builder builder = new Headers.Builder();
        builder.add("X-ApiAuth-ApiKey", objectHandler.apiKey);
        builder.add("X-Date", simpleDateFormat.format(new Date()));
        builder.add("X-Correlation-ID", String.valueOf(new Date().getTime()));
        builder.add("Authorization", bearerToken);

        //Perform POST Method to Xente API.
        OkHttpClient client = new OkHttpClient();
        client.setAuthenticator(new AuthenticatorUtil(credentials, transaction));
        Request requestBody = new Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), transaction.toString()))
                .url(webLink).headers(builder.build()).build();

        //Collect response body from Xente API and return the response body in JSON format.
        Response response = client.newCall(requestBody).execute();
        if(response != null){
            if(response.isSuccessful()){
                try {
                    String body = response.body().string();
                    System.out.println(body+"\ncode: "+response.code());
                    responseBody = new JSONObject(body);
                    return responseBody;
                }
                catch (JSONException e)
                { return null; }
            }
            else
            { return null; }
        }
        else
        { return null; }
    }
}