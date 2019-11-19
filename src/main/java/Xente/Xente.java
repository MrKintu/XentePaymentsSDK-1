/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente;

import Xente.services.*;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@EnableAsync
public class Xente {
    private static JSONObject credentialsObject;
    private static JSONObject transactionObject;

    public Xente (JSONObject credentialsObject, JSONObject transactionObject) {
        Xente.credentialsObject = credentialsObject;
        Xente.transactionObject = transactionObject;
    }

    public static void main(String[] args) throws IOException {
        new AccountsHandler(credentialsObject, transactionObject);
        new PaymentsProvider(credentialsObject, transactionObject);
        new TransactionsHandler(credentialsObject, transactionObject);
        new ObjectHandler(credentialsObject, transactionObject);
        new POSTRequestClient(credentialsObject, transactionObject);
        new URLConstants(credentialsObject, transactionObject);
        new TokenHandler(credentialsObject, transactionObject);
        new GETRequestClient(credentialsObject, transactionObject);
        new AuthenticateUtil(credentialsObject, transactionObject);
    }
}
