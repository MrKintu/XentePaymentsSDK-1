/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK;

import XenteJavaSDK.components.account;
import XenteJavaSDK.components.paymentProvider;
import XenteJavaSDK.components.transaction;
import XenteJavaSDK.services.GETRequestClient;
import XenteJavaSDK.services.constant;
import XenteJavaSDK.services.tokenHandler;
import XenteJavaSDK.services.POSTRequestClient;
import XenteJavaSDK.objectBodies.objectHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@EnableAsync
public class xente {
    xente(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException {
        new account(credentialsObject, transactionObject);
        new paymentProvider(credentialsObject, transactionObject);
        new transaction(credentialsObject, transactionObject);
        new objectHandler(credentialsObject, transactionObject);
        new POSTRequestClient(credentialsObject, transactionObject);
        new constant(credentialsObject);
        new tokenHandler(credentialsObject, transactionObject);
        new GETRequestClient(credentialsObject, transactionObject);
    }
}
