/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.components;

import XenteJavaSDK.services.GETRequestClient;
import XenteJavaSDK.services.constant;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Async
public class account {
    public JSONObject responseBody;

    //Class Constructor
    public account(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException {
        String accountID = "";
        getAccountByID(credentialsObject, transactionObject, accountID);
    }

    //This method is used to get the integrator's account using their account ID.
    //Their account ID would the the phone number they registered to use  the Xente API with.
    //It takes in the credentials object, transaction object and accountID as parameters.
    JSONObject getAccountByID(JSONObject credentials, JSONObject transaction, String accountID) throws IOException, JSONException {
        //Check to see if the user has passed in the accountID.
        if(accountID.isEmpty())
            { System.out.println("Please enter accountID to perform this function."); }
        else {
            constant constant = new constant(credentials);
            String url = constant.accountURL + "/" + accountID;
            GETRequestClient getRequestClient = new GETRequestClient(credentials, transaction);

            getRequestClient.GETMethod(credentials, transaction, url);
            responseBody = getRequestClient.responseBody;
        }

        //return the response body.
        return responseBody;
    }
}
