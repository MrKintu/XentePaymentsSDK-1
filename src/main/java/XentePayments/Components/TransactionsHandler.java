/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments.Components;

import XentePayments.Services.GETRequestClient;
import XentePayments.Services.POSTRequestClient;
import XentePayments.Services.URLConstants;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Async
public class TransactionsHandler {
    //Declare the variables to be accessed globally & locally.
    public JSONObject responseBody;
    private static JSONObject credentialsObject;

    //Class constructor.
    public TransactionsHandler(JSONObject credentialsObject) {
        //Initialise objects.
        TransactionsHandler.credentialsObject = credentialsObject;
    }

    //This method is used to create a new Transaction.
    //It calls the POSTRequestClient to create a new transaction with Xente.
    public JSONObject createTransaction(JSONObject transaction) throws IOException {
        JSONObject credentials = credentialsObject;

        //Attain the URL with which to perform this function.
        URLConstants urlconstants = new URLConstants(credentials);
        final String url = urlconstants.transactionURL;

        //Perform POST Method to send Transactions Request JSON object to Xente and receive a response body.
        POSTRequestClient postRequestClient = new POSTRequestClient(credentials);
        postRequestClient.POSTMethod(transaction,url);

        //Assign the response body to a local variable.
        responseBody = postRequestClient.responseBody;

        //Display the response from the Xente API.
        System.out.println(responseBody);

        //return the response body.
        return responseBody;
    }

    //This method is used get a Transaction using the transaction ID.
    //This methods requires the transactionID.
    //It calls the GETRequestClient to retrieve a response from Xente on the status of their Transactions.
    public JSONObject getTransactionByID(String transactionID) throws IOException {
        JSONObject credentials = credentialsObject;

        //Check to see if the transactionID has been passed to this method.
        if(transactionID.isEmpty())
            { System.out.println("Please insert a Transactions ID to perform this function."); }

        //If passed, continue to get Transaction status from Xente.
        else {
            //Attain the URL with which to perform this function.
            URLConstants urlconstants = new URLConstants(credentials);
            final String url = urlconstants.transactionURL + "?transactionId=" + transactionID + "&PageNumber=1&PageSize=1";

            //Perform GET Method to receive a response body from the Xente API.
            GETRequestClient getRequestClient = new GETRequestClient(credentials);
            getRequestClient.GETMethod(url);

            //Display the responseBody.
            System.out.println(responseBody);

            //Assign the response body to the local variable.
            responseBody = getRequestClient.responseBody;
        }

        //return the response body.
        return responseBody;
    }

    //This method is used to get a transaction using the RequestID.
    //This methods requires the requestID.
    //It calls the GETRequestClient to retrieve a response from Xente on the status of their Transactions.
    public  JSONObject getRequestByID(String requestID) throws IOException {
        JSONObject credentials = credentialsObject;

        //Check to see if the requestID has been passed to this method.
        if(requestID.isEmpty())
            { System.out.println("Please insert a Request ID to perform this function."); }

        //If passed, continue to get Transaction status from Xente.
        else {
            //Attain the URL with which to perform this function.
            URLConstants urlconstants = new URLConstants(credentials);
            final String url = urlconstants.transactionURL + "/Requests/" + requestID;

            //Perform GET Method to receive a response body from the Xente API.
            GETRequestClient getRequestClient = new GETRequestClient(credentials);
            getRequestClient.GETMethod(url);

            //Display the responseBody.
            System.out.println(responseBody);

            //Assign the response body to the local variable.
            responseBody = getRequestClient.responseBody;
        }

        //return the response body.
        return responseBody;
    }
}
