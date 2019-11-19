/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

@Async
public class ObjectHandler {
    //Declare the variables to be accessed globally & locally.
    public String apiKey, password, mode, paymentProvider, amount, message, customerID, customerPhone,
            customerEmail, customerReference, metadata, batchID, requestID;
    private static JSONObject credentialsObject, transactionObject;

    //Class Constructor
    public ObjectHandler(JSONObject credentials, JSONObject transaction) {
        ObjectHandler.credentialsObject = credentials;
        ObjectHandler.transactionObject = transaction;
        authenticationObject();
        transactionObject();
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

    //Method that parses the TransactionsHandler Details Object.
    private String transactionObject() {
        //Initialise object.
        JSONObject object = transactionObject;
        try {
            paymentProvider = (String) object.get("paymentProvider");
            amount = (String) object.get("amount");
            message = (String) object.get("message");
            customerID = (String) object.get("customerId");
            customerPhone =(String) object.get("customerPhone");
            customerEmail = (String) object.get("customerEmail");
            customerReference = (String) object.get("customerReference");
            batchID = (String) object.get("batchId");
            requestID = (String) object.get("requestId");
            metadata = (String) object.get("metadata");
        }

        //Catch all exceptions here.
        catch (JSONException e)
            { System.out.println(e.getMessage()); }

        //Return the data in string format.
        return paymentProvider + amount + message + customerID + customerPhone + customerEmail + customerReference
                + batchID + requestID + metadata;
    }
}
