/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Async
public class GETRequestClient {
    public String webLink;
    public JSONObject responseBody = new JSONObject();
    private String bearerToken;

    //Class Constructor.
    public GETRequestClient(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException
        { GETMethod(credentialsObject, transactionObject, false, null); }

    JSONObject GETMethod(JSONObject credentials, JSONObject transaction, boolean newToken, String link) throws IOException, JSONException {
        //Create local variables to be used.
        tokenHandler tokenHandler = new tokenHandler(credentials, transaction);
        link = webLink;

        //Determine whether bearerToken is available or not
        if(newToken = true || bearerToken.isEmpty()){
            tokenHandler.createToken(credentials, transaction);
            bearerToken = tokenHandler.bearerToken;
        }
        else
            { bearerToken = tokenHandler.bearerToken; }

        //GET Data from Xente API.
        try {
            //Declare the URL to be used.
            URL url = new URL(link);

            //Establish connection too Xente API.
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("Sending 'GET' request to URL : " + link);
            System.out.println("\nResponse Code : " + responseCode);
            BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = input.readLine()) != null) {
                sb.append(inputLine);
            }
            input.close();

            //Display the response body and assign it to variable.
            System.out.println(sb.toString());
            responseBody = new JSONObject(sb.toString());
        }
        catch (Exception e) {
            if(e.getMessage().equals(String.valueOf(401)))  {
                tokenHandler = new tokenHandler(credentials, transaction);
                tokenHandler.createToken(credentials, transaction);
                bearerToken = tokenHandler.bearerToken;
                GETMethod(credentials, transaction, false, null);
            }
            else
            { e.printStackTrace(); }
        }

        //Return the response body.
        return responseBody;
    }
}
