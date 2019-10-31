/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.components;

import XenteJavaSDK.services.GETRequestClient;
import XenteJavaSDK.services.constant;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Async
public class paymentProvider {
    public JSONObject responseBody;

    //Class Constructor.
    public paymentProvider(JSONObject credentialsObject, JSONObject transactionObject) throws IOException, JSONException
        { getPaymentProviders(credentialsObject, transactionObject); }

    JSONObject getPaymentProviders(JSONObject credentials, JSONObject transaction) throws IOException, JSONException {
        //Declare and assign the variables to be used.
        constant constant = new constant(credentials);
        String url = constant.paymentProviderURL;
        GETRequestClient getRequestClient = new GETRequestClient(credentials, transaction);

        //Call GET Method in GETRequestClient and retrieve response body.
        getRequestClient.GETMethod(credentials, transaction, url);
        responseBody = getRequestClient.responseBody;

        //return the response body.
        return responseBody;
    }
}
