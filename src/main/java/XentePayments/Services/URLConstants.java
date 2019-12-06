/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments.Services;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

@Async
public class URLConstants {
    //Declare the variables used
    private static final String productionURL = "https://payments.xente.co/api/v1";
    private static final String sandboxURL = "http://34.90.206.233:83/api/v1";
    private static JSONObject credentialsObject;
    public String authURL, transactionURL, accountURL, paymentProviderURL;

    //Class Constructor
    public URLConstants(JSONObject credentialsObject) {
        //Initialise the objects.
        URLConstants.credentialsObject = credentialsObject;
        getURLs();
    }

    //Determine the type of domain
    private String getURLs() {
        JSONObject credentials = credentialsObject;

        //Determine the baseUrl to be used.
        ObjectHandler objectHandler = new ObjectHandler(credentials);
        String mode = objectHandler.mode;
        String baseUrl;
        if (mode.equals("sandbox"))
            { baseUrl = sandboxURL; }
        else
            { baseUrl = productionURL; }

        //This is the URL called to authenticate a Xente Payments API user.
        authURL = baseUrl + "/Auth/login";

        //This is the URL used to handle transactions.
        transactionURL = baseUrl + "/transactions";

        //This is the URL used to handle the Account details.
        accountURL = baseUrl + "/Accounts";

        //This is the URL used to find out the Payment Providers.
        paymentProviderURL = baseUrl + "/PaymentProviders/MOBILEMONEYUG/providerItems?pageSize=20&pageNumber=1";

        //return the URL values.
        return authURL + transactionURL + accountURL + paymentProviderURL;
    }
}
