/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK;

import XenteJavaSDK.components.Account;
import XenteJavaSDK.components.PaymentProvider;
import XenteJavaSDK.components.Transaction;
import XenteJavaSDK.services.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@EnableAsync
public class Xente {
    Xente(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException {
        new Account(credentialsObject, transactionObject);
        new PaymentProvider(credentialsObject, transactionObject);
        new Transaction(credentialsObject, transactionObject);
        new ObjectHandler(credentialsObject, transactionObject);
        new POSTRequestClient(credentialsObject, transactionObject);
        new ConstantsUtil(credentialsObject, transactionObject);
        new TokenHandler(credentialsObject,transactionObject);
        new GETRequestClient(credentialsObject, transactionObject);
        new AuthenticatorUtil(credentialsObject, transactionObject);
    }
}
