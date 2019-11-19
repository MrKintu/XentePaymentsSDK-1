/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente;

import Xente.services.GETRequestClient;
import Xente.services.URLConstants;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Async
public class AccountsHandler {
    //Declare the variables to be accessed globally & locally.
    public JSONObject responseBody;
    private static JSONObject credentialsObject, transactionObject;

    //Class Constructor
    public AccountsHandler(JSONObject credentialsObject, JSONObject transactionObject) {
        AccountsHandler.credentialsObject = credentialsObject;
        AccountsHandler.transactionObject = transactionObject;
    }

    //This method is used to get the integrator's AccountsHandler using their AccountsHandler ID.
    //Their AccountsHandler ID would the the phone number they registered to use  the Xente API with.
    //It takes in the credentials object, TransactionsHandler object and accountID as parameters.
    public JSONObject getAccountByID(String accountID) throws IOException {
        JSONObject credentials = credentialsObject;
        JSONObject transaction = transactionObject;

        //Attain the URL with which to perform this function.
        URLConstants urlconstants = new URLConstants(credentials, transaction);
        final String url = urlconstants.accountURL + "/" + accountID;

        //Perform GET Method to receive a response body from the Xente API.
        GETRequestClient getRequestClient = new GETRequestClient(credentials, transaction);
        getRequestClient.GETMethod(url);

        //Assign the response body to the local variable.
        responseBody = getRequestClient.responseBody;

        //Display the responseBody.
        System.out.println(responseBody);

        //return the response body.
        return responseBody;
    }
}
