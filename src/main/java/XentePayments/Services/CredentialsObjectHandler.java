/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments.Services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

@Async
public class CredentialsObjectHandler {
    //Declare the variables to be accessed globally & locally.
    public String apiKey, password, mode, paymentProvider, amount, message, customerID, customerPhone, customerEmail,
            customerReference, metadata, batchID, requestID;
    private static JSONObject credentialsObject;

    //Class Constructor
    public CredentialsObjectHandler(JSONObject credentials) {
        //Initialise objects and methods.
        CredentialsObjectHandler.credentialsObject = credentials;
        authenticationObject();
    }

    //Method that parses the Authentication Credentials
    private String authenticationObject() {
        //Initialise object.
        JSONObject object = credentialsObject;
        try {
            apiKey = (String) object.get("apiKey");
            password = (String) object.get("password");
            mode = (String) object.get("mode");
        }

        //Catch all exceptions here.
        catch (JSONException e)
            { System.out.println(e.getMessage()); }

        //Return data in String format.
        return apiKey + password + mode;
    }
}
