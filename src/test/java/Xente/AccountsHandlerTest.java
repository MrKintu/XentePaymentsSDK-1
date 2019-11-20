/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente;

import Xente.Components.AccountsHandler;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

//Test to determine the account information of dummy user
class AccountsHandlerTest {
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

        //Invoke method.
        AccountsHandler accountsHandler = new AccountsHandler(credentials, transaction);
        String accountID = "256784378515";
        accountsHandler.getAccountByID(accountID);
    }
}