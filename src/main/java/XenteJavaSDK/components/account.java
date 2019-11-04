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
    //Declare the variables to be used.
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

        //If passed, continue to get transaction status from Xente.
        else {
            //Attain the URL with which to perform this function.
            constant constant = new constant(credentials, transaction);
            String url = constant.accountURL + "/" + accountID;

            //Perform GET Method to receive a response body from the Xente API.
            GETRequestClient getRequestClient = new GETRequestClient(credentials, transaction);
            getRequestClient.GETMethod(credentials, transaction, url);

            //Assign the response body to the local variable.
            responseBody = getRequestClient.responseBody;
        }

        //return the response body.
        return responseBody;
    }
}
