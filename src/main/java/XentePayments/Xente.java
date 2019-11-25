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
    private static JSONObject transactionRequest;
    public final AccountsHandler accountsHandler;
    public final PaymentsProvider paymentsProvider;
    public final TransactionsHandler transactionsHandler;

    public Xente(JSONObject credentialsObject, JSONObject transactionRequest) {
        //Initialise object variables.
        Xente.credentialsObject = credentialsObject;
        Xente.transactionRequest = transactionRequest;
        accountsHandler = new AccountsHandler(credentialsObject, transactionRequest);
        paymentsProvider = new PaymentsProvider(credentialsObject, transactionRequest);
        transactionsHandler = new TransactionsHandler(credentialsObject, transactionRequest);
    }

    public static void main(String[] args) throws IOException {
        //Assign objects to the classes within the SDK.
        new AccountsHandler(credentialsObject, transactionRequest);
        new PaymentsProvider(credentialsObject, transactionRequest);
        new TransactionsHandler(credentialsObject, transactionRequest);
        new ObjectHandler(credentialsObject, transactionRequest);
        new POSTRequestClient(credentialsObject, transactionRequest);
        new URLConstants(credentialsObject, transactionRequest);
        new TokenHandler(credentialsObject, transactionRequest);
        new GETRequestClient(credentialsObject, transactionRequest);
        new AuthenticateUtil(credentialsObject, transactionRequest);
    }
}
