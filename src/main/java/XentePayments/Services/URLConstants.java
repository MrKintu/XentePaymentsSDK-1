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
    private static final String baseDomain = "api.Xente.co";
    private static final String baseSandboxDomain = "sandbox." + baseDomain;
    private static JSONObject credentialsObject, transactionObject;
    public String baseUrl, authURL, transactionURL, accountURL, paymentProviderURL;

    //Class Constructor
    public URLConstants(JSONObject credentialsObject, JSONObject transactionObject) {
        //Initialise the objects.
        URLConstants.credentialsObject = credentialsObject;
        URLConstants.transactionObject = transactionObject;
        getURLs();
    }

    //Determine the type of domain
    private String getURLs() {
        JSONObject credentials = credentialsObject;
        JSONObject transaction = transactionObject;

        //Determine the domain to be used.
        ObjectHandler objectHandler = new ObjectHandler(credentials, transaction);
        String mode = objectHandler.mode;
        String domain;
        if (mode.equals("sandbox"))
            { domain = baseSandboxDomain; }
        else
            { domain = baseDomain; }

        //Assign the base URL.
        baseUrl = "https://" + domain + "/api/v1";

        // `${baseUrl}/Auth/login`; Will be used instead
        authURL = "http://34.90.206.233:83/api/v1/Auth/login";

        // `${baseUrl}/transactions`; Will be used instead
        transactionURL = "http://34.90.206.233:83/api/v1/transactions";

        // This will be `${baseUrl}/Accounts`
        accountURL = "http://34.90.206.233:83/api/v1/Accounts";

        // This will be `${baseUrl}/paymentproviders`
        paymentProviderURL = "http://34.90.206.233:83/api/v1/PaymentProviders/MOBILEMONEYUG/providerItems?pageSize=20&pageNumber=1";

        //return the URL values.
        return baseUrl + authURL + transactionURL + accountURL + paymentProviderURL;
    }
}
