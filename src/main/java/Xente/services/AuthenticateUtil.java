/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente.services;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.json.JSONObject;

import java.io.IOException;

public class AuthenticateUtil implements Authenticator {
    private JSONObject credentialsObject;
    private JSONObject transactionObject;

    public AuthenticateUtil(JSONObject credentialsObject, JSONObject transactionObject) {
        this.credentialsObject = credentialsObject;
        this.transactionObject = transactionObject;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        TokenHandler tokenHandler = new TokenHandler(credentialsObject, transactionObject);
        tokenHandler.createToken(credentialsObject, transactionObject);
        String bearerToken = tokenHandler.bearerToken;

        return response.request().newBuilder().addHeader("Authorization", "Bearer "+bearerToken).build();
    }
}
