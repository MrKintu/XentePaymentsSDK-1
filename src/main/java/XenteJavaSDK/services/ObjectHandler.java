/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

@Async
public class ObjectHandler {
    //Declare the variables to be accessed globally.
    public String apiKey, password, mode, paymentProvider, amount, message, customerID, customerPhone,
            customerEmail, customerReference, metadata, batchID, requestID;

    //Class Constructor
    public ObjectHandler(JSONObject credentials, JSONObject transaction) {
        authenticationObject(credentials);
        transactionObject(transaction);
    }

    //Method that parses the Authentication Credentials
    private String authenticationObject(JSONObject object) {
        try {
            apiKey = (String) object.get("apiKey");
            password = (String) object.get("password");
            mode = (String) object.get("mode");
        }

        //Catch all exceptions here.
        catch (JSONException e)
            { e.printStackTrace(); }

        //Return data in String format.
        return apiKey + password + mode;
    }

    //Method that parses the Transaction Details Object.
    private String transactionObject(JSONObject object) {
        try {
            paymentProvider = (String) object.get("PaymentProvider");
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
            { e.printStackTrace(); }

        //Return the data in string format.
        return paymentProvider + amount + message + customerID + customerPhone + customerEmail
                + customerReference + batchID + requestID + metadata;
    }
}
