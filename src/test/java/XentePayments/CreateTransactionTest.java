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

//Test to create a sample transaction.
class CreateTransactionTest {
    public static void main(String[] args) throws JSONException, IOException {
        //Test credentials object.
        JSONObject credentials = new JSONObject();
        credentials.put("apiKey", "2E06507DED4C4CE48CE260FBBB28F95D");
        credentials.put("password", "XentE123456");
        credentials.put("mode", "production");

        //Test transaction object.
        JSONObject transaction = new JSONObject();
        transaction.put("paymentProvider", "MTNMOBILEMONEYUG");
        transaction.put("amount", "2000");
        transaction.put("message", "Demo Request");
        transaction.put("customerId", "256778418592");
        transaction.put("customerPhone", "256784378515");
        transaction.put("customerEmail", "d-kintu@outlook.com");
        transaction.put("customerReference", "256784378515");
        transaction.put("batchId", "Batch001");
        transaction.put("requestId", String.valueOf(Math.random()));
        transaction.put("metadata", "More information about TransactionsHandler here");

        //Invoke method.
        TransactionsHandler transactionsHandler = new TransactionsHandler(credentials);
        transactionsHandler.createTransaction(transaction);
    }
}