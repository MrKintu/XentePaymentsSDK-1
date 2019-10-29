/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * This software is open for usage and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.services;

//These are the import statements.
import XenteJavaSDK.objectBodies.authCredentialsObject;
import XenteJavaSDK.objectBodies.transactionCredentialsObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class objectHandler {
    String apiKey, password, mode, paymentProvider, amount, message, customerID, customerPhone,
            customerEmail, customerReference, metadata, batchID, requestID;

    //Class Constructor
    public objectHandler(JSONObject credentialsObject) {
        authenticationObject(credentialsObject);
        transactionObject(credentialsObject);
    }

    //Method that collects the Authentication Credentials
    private String authenticationObject(JSONObject object) {
        try {
            //Read data from JSON Format
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(String.valueOf(object));
            TypeReference<List<authCredentialsObject>> typeReference = new TypeReference<List<authCredentialsObject>>() {
                @Override
                public Type getType()
                    { return super.getType(); }
            };
            List<authCredentialsObject> authObject = mapper.readValue(inputStream, typeReference);

            //Manipulate the object data
            for (authCredentialsObject obj : authObject) {
                //Assign data to variables
                apiKey = obj.getApiKey();
                password = obj.getPassword();
                mode = obj.getMode();

                //Display the data in the console
                System.out.println("apiKey: " + obj.getApiKey()
                        + "\npassword: " + obj.getPassword()
                        + "\nmode: " + obj.getMode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiKey + password + mode;
    }

    //Method that handles the Transaction Details Object.
    private String transactionObject(JSONObject object) {
        try {
            //Read data from JSON Format
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(String.valueOf(object));
            TypeReference<List<transactionCredentialsObject>> typeReference = new TypeReference<List<transactionCredentialsObject>>() {
                @Override
                public Type getType()
                { return super.getType(); }
            };
            List<transactionCredentialsObject> transactionObject = mapper.readValue(inputStream, typeReference);

            //Manipulate the object data
            for (transactionCredentialsObject obj : transactionObject) {
                //Assign data to variables
                paymentProvider = obj.getPaymentProvider();
                amount = obj.getAmount();
                message = obj.getMessage();
                customerID = obj.getCustomerID();
                customerPhone = obj.getCustomerPhone();
                customerEmail = obj.getCustomerEmail();
                customerReference = obj.getCustomerReference();
                batchID = obj.getBatchID();
                requestID = obj.getRequestID();
                metadata = obj.getMetadata();

                //Display the data in the console
                System.out.println("paymentProvider: " + obj.getPaymentProvider()
                        + "\namount: " + obj.getAmount()
                        + "\nmessage: " + obj.getMessage()
                        + "\ncustomerID: " + obj.getCustomerID()
                        + "\ncustomerPhone: " + obj.getCustomerPhone()
                        + "\ncustomerEmail: " + obj.getCustomerEmail()
                        + "\ncustomerReference: " + obj.getCustomerReference()
                        + "\nbatchID: " + obj.getBatchID()
                        + "\nrequestID: " + obj.getRequestID()
                        + "\nmetadata: " + obj.getMetadata()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paymentProvider + amount + message + customerID + customerPhone + customerEmail
                + customerReference + metadata + batchID + requestID;
    }
}
