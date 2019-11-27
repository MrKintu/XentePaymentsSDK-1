/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments.Services;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Async
public class GETRequestClient {
    //Declare the object variables to be accessed globally & locally.
    public JSONObject responseBody;
    private static JSONObject credentialsObject;

    //Class Constructor.
    public GETRequestClient(JSONObject credentialsObject) {
        //Initialise objects.
        GETRequestClient.credentialsObject = credentialsObject;
    }

    // Create a Http object for making GET HTTP request to Xente API.
    //It takes in the Credentials object, the TransactionsHandler object and the respective URL as parameters.
    public JSONObject GETMethod(String url) throws IOException {
        //Create local variables to be used.
        JSONObject credentials = credentialsObject;
        ObjectHandler objectHandler = new ObjectHandler(credentials);
        TokenHandler tokenHandler = new TokenHandler(credentials);
        String bearerToken = tokenHandler.bearerToken;

//        Determine whether bearerToken is available or not.
        if(bearerToken.isEmpty()) {
            tokenHandler.createToken();
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
        OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();
        okbuilder.authenticator(new AuthenticateUtil(credentials));
        OkHttpClient client = okbuilder.build();
        Request requestBody = new Request.Builder().get().url(url).headers(builder.build()).build();

        //Collect response body from Xente API and return the response body in JSON format.
        Response response = client.newCall(requestBody).execute();
        if(response != null) {
            if(response.isSuccessful()) {
                try {
                    assert response.body() != null;
                    String body = response.body().string();
                    System.out.println(body + "\ncode: " + response.code());
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
