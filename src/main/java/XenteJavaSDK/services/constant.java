/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import XenteJavaSDK.objectBodies.authCredentialsObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

@Async
public class constant {
    //Declare the variables used
    private static final String baseDomain = "api.xente.co";
    private static final String baseSandboxDomain = "sandbox." + baseDomain;
    private String domain;
    public String baseUrl, authURL, transactionURL, accountURL, paymentProviderURL;

    //Class Constructor
    public constant(JSONObject credentialsObject)
        { getURLs(credentialsObject); }

    //Determine the type of domain
    String getURLs(JSONObject object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(String.valueOf(object));
            TypeReference<List<authCredentialsObject>> typeReference = new TypeReference<List<authCredentialsObject>>() {
                @Override
                public Type getType()
                { return super.getType(); }
            };
            List<authCredentialsObject> authObject = mapper.readValue(inputStream, typeReference);

            for (authCredentialsObject obj : authObject) {
                String sandbox = obj.getMode();
                if (sandbox.equals("true")) {
                    domain = baseSandboxDomain;
                } else {
                    domain = baseDomain;
                }
            }

            //Assign the base URL.
            baseUrl = "https://" + domain + "/api/v1";

            // `${baseUrl}/Auth/login`; Will be used instead
            authURL = "http://34.90.206.233:83/api/v1/Auth/login";

            // `${baseUrl}/transactions`; Will be used instead
            transactionURL = "http://34.90.206.233:83/api/v1/transactions";

            // This will be `${baseUrl}/Accounts`
            accountURL = "http://34.90.206.233:83/api/v1/Accounts";

            // This will be `${baseUrl}/paymentproviders`
            paymentProviderURL = "http://34.90.206.233:83/api/v1/paymentproviders";
        }
        catch (IOException e)
            { e.printStackTrace(); }

        return baseUrl + authURL + transactionURL + accountURL + paymentProviderURL;
    }
}
