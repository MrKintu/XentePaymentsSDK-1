/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.objectBodies;

import com.sun.istack.internal.NotNull;
import org.springframework.scheduling.annotation.Async;

@Async
public class transactionCredentialsObject {
    //Declare the variables to be used.
    @NotNull
    private String paymentProvider, amount, message, customerID, customerPhone, customerEmail, customerReference,
    metadata, batchID, requestID;

    //Implement Getter and Setter for all Key-Value pairs
    public String getPaymentProvider()
        { return paymentProvider; }

    public String getAmount()
        { return amount; }

    public String getMessage()
        { return message; }

    public String getCustomerID()
        { return customerID; }

    public String getCustomerPhone()
        { return customerPhone; }

    public String getCustomerEmail()
        { return customerEmail; }

    public String getCustomerReference()
        { return customerReference; }

    public String getMetadata()
        { return metadata; }

    public String getBatchID()
        { return batchID; }

    public String getRequestID()
        { return requestID; }
}
