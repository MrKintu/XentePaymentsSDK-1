/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente.services;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Proxy;

public class AuthenticatorUtil implements com.squareup.okhttp.Authenticator {
    private JSONObject credentialsObject;
    private JSONObject transactionObject;

    public AuthenticatorUtil(JSONObject credentialsObject, JSONObject transactionObject) {
        this.credentialsObject = credentialsObject;
        this.transactionObject = transactionObject;
    }

    @Override
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        TokenHandler tokenHandler = new TokenHandler(credentialsObject, transactionObject);
        tokenHandler.createToken(credentialsObject, transactionObject);
        String bearerToken = tokenHandler.bearerToken;

        return response.request().newBuilder().addHeader("Authorization", bearerToken).build();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response)
        { return null; }
}
