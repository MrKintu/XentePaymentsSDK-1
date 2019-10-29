/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * This software is open for usage and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

import XenteJavaSDK.objectBodies.authCredentialsObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class constant {
    //Declare the variables used
    private static final String baseDomain = "api.xente.co";
    private static final String baseSandboxDomain = "sandbox." + baseDomain;
    private String domain;
    String baseUrl, authURL, transactionURL, accountURL, paymentProviderURL;

    //Class Constructor
    public constant(JSONObject credentialsObject) {
        getDomain(credentialsObject);
    }

    //Determine the type of domain
    String getDomain(JSONObject object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(String.valueOf(object));
            TypeReference<List<authCredentialsObject>> typeReference = new TypeReference<List<authCredentialsObject>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            };
            List<authCredentialsObject> authObject = mapper.readValue(inputStream, typeReference);

            for (authCredentialsObject obj : authObject) {
                String sandbox = obj.getMode();
                if (sandbox.equals("true")) {
                    domain = constant.baseSandboxDomain;
                } else {
                    domain = constant.baseDomain;
                }
            }

            baseUrl = "https://" + domain + "/api/v1";

            // `${baseUrl}/Auth/login`; Will be used instead
            authURL = "http://34.90.206.233:83/api/v1/Auth/login";

            // `${baseUrl}/transactions`; Will be used instead
            transactionURL = "http://34.90.206.233:83/api/v1/transactions";

            // This will be `${baseUrl}/Accounts`
            accountURL = "http://34.90.206.233:83/api/v1/Accounts";

            // This will be `${baseUrl}/paymentproviders`
            paymentProviderURL = "http://34.90.206.233:83/api/v1/paymentproviders";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baseUrl + authURL + transactionURL + accountURL + paymentProviderURL;
    }
}
