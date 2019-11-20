/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente;

import Xente.Components.AccountsHandler;
import Xente.Components.PaymentsProvider;
import Xente.Components.TransactionsHandler;
import Xente.Services.*;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@EnableAsync
public class Xente {
    private static JSONObject credentialsObject;
    private static JSONObject transactionObject;
    public final AccountsHandler accountsHandler;
    public final PaymentsProvider paymentsProvider;
    public final TransactionsHandler transactionsHandler;

    public Xente(JSONObject credentialsObject, JSONObject transactionObject) {
        Xente.credentialsObject = credentialsObject;
        Xente.transactionObject = transactionObject;
        accountsHandler = new AccountsHandler(credentialsObject, transactionObject);
        paymentsProvider = new PaymentsProvider(credentialsObject, transactionObject);
        transactionsHandler = new TransactionsHandler(credentialsObject, transactionObject);
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
