/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments.Services;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

//This class is used to re-authenticate the user in case the HTTP Response Code is < than 400.
@EnableAsync
public class AuthenticateUtil implements Authenticator {
    //Create Objects to be used throughout the class.
    private static JSONObject credentialsObject;
    private static JSONObject transactionObject;

    public AuthenticateUtil(JSONObject credentialsObject, JSONObject transactionObject) {
        //Initialise objects.
        AuthenticateUtil.credentialsObject = credentialsObject;
        AuthenticateUtil.transactionObject = transactionObject;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        TokenHandler tokenHandler = new TokenHandler(credentialsObject, transactionObject);
        tokenHandler.createToken();
        String bearerToken = tokenHandler.bearerToken;

        return response.request().newBuilder().addHeader("Authorization", "Bearer "+bearerToken).build();
    }
}
