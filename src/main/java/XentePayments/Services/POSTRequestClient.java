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

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Async
public class POSTRequestClient {
    //Declare the object variables to be accessed globally & locally.
    public JSONObject responseBody;
    private static JSONObject credentialsObject;

    //Class Constructor.
    public POSTRequestClient(JSONObject credentialsObject) {
        //Initialise objects.
        POSTRequestClient.credentialsObject = credentialsObject;
    }

    // Create a Http object for making POST HTTP request to Xente API.
    //It takes in the Respective URL as parameters.
    public JSONObject POSTMethod(JSONObject transaction, String url) throws IOException {
        //Create local variables to be used.
        JSONObject credentials = credentialsObject;
        CredentialsObjectHandler credentialsObjectHandler = new CredentialsObjectHandler(credentials);
        TokenHandler tokenHandler = new TokenHandler(credentials);
        String bearerToken = tokenHandler.bearerToken;

        //Determine whether bearerToken is available or not.
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
        builder.add("X-ApiAuth-ApiKey", credentialsObjectHandler.apiKey);
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