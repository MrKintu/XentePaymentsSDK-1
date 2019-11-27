/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments;

import XentePayments.Components.TransactionsHandler;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

//Test to retrieve transaction details using the requestID.
class GetRequestIDTest {
    public static void main(String[] args) throws JSONException, IOException {
        //Test credentials object.
        JSONObject credentials = new JSONObject();
        credentials.put("apiKey", "6A19EA2A706041A599375CC95FF08809");
        credentials.put("password", "Demo123456");
        credentials.put("mode", "sandbox");

        //Invoke method.
        TransactionsHandler transactionsHandler = new TransactionsHandler(credentials);
        String requestID = "0.9351612896255068";
        transactionsHandler.getRequestByID(requestID);
    }
}