/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * Written on behalf of Xente Limited.
 * This software is open for use and modification by any user.
 * Thank you.
 */

package XentePayments.Services;

public class ObjectBuilder {
    //Declare the variables to be accessed globally & locally.
    public String apiKey, password, mode, paymentProvider, amount, message, customerID, customerPhone,
            customerEmail, customerReference, metadata, batchID, requestID;

    public String getApiKey()
        { return apiKey; }

    public void setApiKey(String apiKey)
        { this.apiKey = apiKey; }


    public String getPassword()
        { return password; }

    public void setPassword(String password)
        { this.password = password; }


    public String getMode()
        { return mode; }

    public void setMode(String mode)
        { this.mode = mode; }


    public String getPaymentProvider()
        { return paymentProvider; }

    public void setPaymentProvider(String paymentProvider)
        { this.paymentProvider = paymentProvider; }


    public String getAmount()
        { return amount; }

    public void setAmount(String amount)
        { this.amount = amount; }


    public String getMessage()
        { return message; }

    public void setMessage(String message)
        { this.message = message; }


    public String getCustomerID()
        { return customerID; }

    public void setCustomerID(String customerID)
        { this.customerID = customerID; }


    public String getCustomerPhone()
        { return customerPhone; }

    public void setCustomerPhone(String customerPhone)
        { this.customerPhone = customerPhone; }


    public String getCustomerEmail()
        { return customerEmail; }

    public void setCustomerEmail(String customerEmail)
        { this.customerEmail = customerEmail; }


    public String getCustomerReference()
        { return customerReference; }

    public void setCustomerReference(String customerReference)
        { this.customerReference = customerReference; }


    public String getMetadata()
        { return metadata; }

    public void setMetadata(String metadata)
        { this.metadata = metadata; }


    public String getBatchID()
        { return batchID; }

    public void setBatchID(String batchID)
        { this.batchID = batchID; }


    public String getRequestID()
        { return requestID; }

    public void setRequestID(String requestID)
        { this.requestID = requestID; }
}
