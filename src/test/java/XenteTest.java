/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

import XentePayments.Xente;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

class XenteTest {
    public static void main(String[] args) throws JSONException, IOException {
        //Test credentials object.
        JSONObject credentials = new JSONObject();
        credentials.put("apiKey", "6A19EA2A706041A599375CC95FF08809");
        credentials.put("password", "Demo123456");
        credentials.put("mode", "sandbox");

        //Test transaction object.
        JSONObject transaction = new JSONObject();
        transaction.put("paymentProvider", "MTNMOBILEMONEYUG");
        transaction.put("amount", "800");
        transaction.put("message", "Demo Request");
        transaction.put("customerId", "256778418592");
        transaction.put("customerPhone", "256778418592");
        transaction.put("customerEmail", "d-kintu@outlook.com");
        transaction.put("customerReference", "256778418592");
        transaction.put("batchId", "Batch001");
        transaction.put("requestId", String.valueOf(Math.random()));
        transaction.put("metadata", "More information about TransactionsHandler here");

        //Create Xente Object.
        Xente xente = new Xente(credentials);

//        //Invoke method to check account details.
//        String accountID = "256784378515";
//        xente.accountsHandler.getAccountByID(accountID);

//        //Invoke method to list payment providers.
//        xente.paymentProviders.getPaymentProviders();

        //Invoke method to create transaction.
        xente.transactionsHandler.createTransaction(transaction);

//        //Invoke method to get a transaction using the transaction ID.
//        String transactionID = "631034D3F96C441085FA7D010ACB7194-256784378515";
//        xente.transactionsHandler.getTransactionByID(transactionID);

//        //Invoke method to get a transaction using the Request ID.
//        String requestID = "0.8230077495954705";
//        xente.transactionsHandler.getRequestByID(requestID);
    }
}