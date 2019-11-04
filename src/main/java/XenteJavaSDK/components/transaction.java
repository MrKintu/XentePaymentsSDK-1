/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.components;

import XenteJavaSDK.services.GETRequestClient;
import XenteJavaSDK.services.POSTRequestClient;
import XenteJavaSDK.services.constant;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Async
public class transaction {
    //Declare the variables to be used.
    public JSONObject responseBody;

    //Class constructor.
    public transaction(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException {
        String newID = "";
        createTransaction(credentialsObject, transactionObject);
        getTransactionByID(credentialsObject,transactionObject, newID);
    }

    //This method is used to create a new transaction.
    //This methods requires the authentication credentials JSON and the transaction credentials JSON.
    //It calls the GETRequestClient to retrieve a response from Xente on the status of their transaction.
    JSONObject createTransaction(JSONObject credentials, JSONObject transaction) throws IOException, JSONException {
        //Attain the URL with which to perform this function.
        constant constant = new constant(credentials, transaction);
        String url = constant.transactionURL;

        //Perform POST Method to send transaction JSON object to Xente and receive a response body.
        POSTRequestClient postRequestClient = new POSTRequestClient(credentials, transaction);
        postRequestClient.POSTMethod(credentials, transaction, url);

        //Assign the response body to a local variable.
        responseBody = postRequestClient.responseBody;

        //return the response body.
        return responseBody;
    }

    //This method is used get a transaction using the transactionID.
    //This methods requires the authentication credentials JSON, the transaction credentials JSON and the transactionID.
    //It calls the POSTRequestClient to retrieve a response from Xente on the status of their transaction.
    JSONObject getTransactionByID(JSONObject credentials, JSONObject transaction, String transactionID) throws IOException, JSONException {
        //Check to see if the transactionID has been passed to this method.
        if(transactionID.isEmpty())
            { System.out.println("Please insert a Transaction ID to perform this function."); }

        //If passed, continue to get transaction status from Xente.
        else {
            //Attain the URL with which to perform this function.
            constant constant = new constant(credentials, transaction);
            String url = constant.transactionURL + "?transactionId=" + transactionID + "&PageSize=1&PageNumber=1";

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
