/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments.Components;

import XentePayments.Services.GETRequestClient;
import XentePayments.Services.URLConstants;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

@Async
public class PaymentsProvider {
    //Declare the variables to be accessed globally & locally.
    public JSONObject responseBody;
    private static JSONObject credentialsObject, transactionRequest;

    //Class Constructor.
    public PaymentsProvider(JSONObject credentialsObject, JSONObject transactionRequest) {
        //Initialise objects.
        PaymentsProvider.credentialsObject = credentialsObject;
        PaymentsProvider.transactionRequest = transactionRequest;
    }

    //This method is used to list all the payment providers available through the Xente API.
    public JSONObject getPaymentProviders() throws IOException {
        JSONObject credentials = credentialsObject;
        JSONObject transaction = transactionRequest;

        //Declare the URL to be used.
        URLConstants urlconstants = new URLConstants(credentials, transaction);
        final String url = urlconstants.paymentProviderURL;

        //Call GET Method in GETRequestClient and retrieve response body.
        GETRequestClient getRequestClient = new GETRequestClient(credentials, transaction);
        getRequestClient.GETMethod(url);

        //Assign the response body to a local variable.
        responseBody = getRequestClient.responseBody;

        //Display the responseBody.
        System.out.println(responseBody);

        //return the response body.
        return responseBody;
    }
}
