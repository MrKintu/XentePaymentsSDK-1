/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package Xente;

import Xente.services.GETRequestClient;
import Xente.services.URLConstants;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Async
public class PaymentsProvider {
    //Declare the variables to be accessed globally.
    public JSONObject responseBody;

    //Class Constructor.
    public PaymentsProvider(JSONObject credentialsObject, JSONObject transactionObject) throws IOException
        { getPaymentProviders(credentialsObject, transactionObject); }

    public JSONObject getPaymentProviders(JSONObject credentials, JSONObject transaction) throws IOException {
        //Declare the URL to be used.
        URLConstants urlconstants = new URLConstants(credentials, transaction);
        String url = urlconstants.paymentProviderURL;

        //Call GET Method in GETRequestClient and retrieve response body.
        GETRequestClient getRequestClient = new GETRequestClient(credentials, transaction);
        getRequestClient.GETMethod(credentials, transaction, url);

        //Assign the response body to a local variable.
        responseBody = getRequestClient.responseBody;

        //return the response body.
        return responseBody;
    }
}
