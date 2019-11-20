/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente.Services;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Async
public class POSTRequestClient {
    //Declare the variables to be accessed globally & locally.
    public JSONObject responseBody;
    private static JSONObject credentialsObject, transactionObject;

    //Class Constructor.
    public POSTRequestClient(JSONObject credentialsObject, JSONObject transactionObject) {
        POSTRequestClient.credentialsObject = credentialsObject;
        POSTRequestClient.transactionObject = transactionObject;
    }

    // Create a Http object for making POST HTTP request to Xente API.
    //It takes in the Credentials object, the TransactionsHandler object and the respective URL as parameters.
    public JSONObject POSTMethod(String url) throws IOException {
        //Create local variables to be used.
        JSONObject credentials = credentialsObject;
        JSONObject transaction = transactionObject;
        ObjectHandler objectHandler = new ObjectHandler(credentials, transaction);
        TokenHandler tokenHandler = new TokenHandler(credentials, transaction);
        String bearerToken = tokenHandler.bearerToken;

        //Determine whether bearerToken is available or not.
//        if(bearerToken.isEmpty()) {
//            tokenHandler.createToken(credentials, transaction);
//            bearerToken = tokenHandler.bearerToken;
//        }
//        else
//            { bearerToken = tokenHandler.bearerToken; }

        //Create custom date format for Xente API.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        //Build header section to be sent to the Xente API.
        Headers.Builder builder = new Headers.Builder();
        builder.add("X-ApiAuth-ApiKey", objectHandler.apiKey);
        builder.add("X-Date", simpleDateFormat.format(new Date()));
        builder.add("X-Correlation-ID", String.valueOf(new Date().getTime()));
        builder.add("Authorization", "Bearer "+bearerToken);
        builder.add("Content-Type", "application/json");

//        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
//        mBuilder.authenticator(new AuthenticateUtil(credentials, transaction));
//        mBuilder.addInterceptor(chain -> {
//
//            Request request = chain.request();
//            System.out.println("URL:"+request.url().toString());
//            return chain.proceed(request);
//        });

        //Perform POST Method to Xente API.
        OkHttpClient client = new OkHttpClient();
//        OkHttpClient client = mBuilder.build();
//        client.setAuthenticator(new AuthenticatorUtil(credentials, transaction));
        Request requestBody = new Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), transaction.toString()))
                .url(url).headers(builder.build()).build();

        //Collect response body from Xente API and return the response body in JSON format.
        Response response = client.newCall(requestBody).execute();
        if(response != null) {
            if(response.isSuccessful()) {
                try {
                    assert response.body() != null;
                    String body = response.body().string();
                    responseBody = new JSONObject(body);
                    return responseBody;
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