/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * This software is open for usage and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import jdk.nashorn.internal.parser.Token;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class httpRequestClient {
    public Token bearerToken;
    private objectHandler objectHandler;
    private JSONObject httpOptions = new JSONObject();

    //Class Constructor
    public httpRequestClient(JSONObject credentialsObject) {
        constant constant = new constant(credentialsObject);
        constant.getDomain(credentialsObject);
    }

    // Create a Http object for making request
    private JSONObject httpObject() {
        JSONObject object = new JSONObject();

        //Add data to the Http Object
        try {
            //Create object for the Header section in the httpObject.
            object.put("Content-Type", "application/json");
            object.put("X-ApiAuth-ApiKey", objectHandler.apiKey);
            object.put("X-Date", new Date().toString());
            object.put("X-Correlation-ID", new Date().getTime());
            object.put("Authorization", "null");

            //Add values to httpObject.
            httpOptions.put("method", "null");
            httpOptions.put("headers", object);
            httpOptions.put("body", "null");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return httpOptions;
    }

    public Token tokenStore()
        {return bearerToken;}
}