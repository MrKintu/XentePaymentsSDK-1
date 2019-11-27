/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments;

import XentePayments.Components.AccountsHandler;
import XentePayments.Components.PaymentsProvider;
import XentePayments.Components.TransactionsHandler;
import XentePayments.Services.*;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@EnableAsync
public class Xente {
    //Create object Variables to be accessed locally and globally within the SDK.
    private static JSONObject credentialsObject;
//    private static JSONObject transactionRequest;
    public final AccountsHandler accountsHandler;
    public final PaymentsProvider paymentsProvider;
    public final TransactionsHandler transactionsHandler;

    public Xente(JSONObject credentialsObject) {
        //Initialise object variables.
        Xente.credentialsObject = credentialsObject;
//        Xente.transactionRequest = transactionRequest;
        accountsHandler = new AccountsHandler(credentialsObject);
        paymentsProvider = new PaymentsProvider(credentialsObject);
        transactionsHandler = new TransactionsHandler(credentialsObject);
    }

    public static void main(String[] args) throws IOException {
        //Assign objects to the classes within the SDK.
        new AccountsHandler(credentialsObject);
        new PaymentsProvider(credentialsObject);
        new TransactionsHandler(credentialsObject);
        new ObjectHandler(credentialsObject);
        new POSTRequestClient(credentialsObject);
        new URLConstants(credentialsObject);
        new TokenHandler(credentialsObject);
        new GETRequestClient(credentialsObject);
        new AuthenticateUtil(credentialsObject);
    }
}
