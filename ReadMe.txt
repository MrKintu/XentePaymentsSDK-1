Hosted on:
https://oss.sonatype.org/service/local/staging/deployByRepositoryId/orgbitbucketdkintu-1001/org/bitbucket/dkintu/XentePaymentsSDK/1.0.1-SNAP

This is a guide on how to use the XentePayments Java SDK.

- The SDK is divided into two main parts: the Components and the Services.
- The SDK user shall mainly interact with the Components package and the Services package shall run in the background.
- The XentePayments package takes in two JSON files as parameters for the SDK to work: Credentials JSON File & Transaction JSON File.
- The Credentials JSON has information about the integrator's XentePayments log in while the Transaction JSON contains information
    about the transaction that the user would like to perform.

- The Credentials JSON looks as such:
{
    "apiKey": "6A19EA2A706041A599375CC95FF08809",
    "password": "Demo123456",
    "mode": "sandbox"
}

- The Transaction JSON looks as such:
{
    "paymentProvider": "MTNMOBILEMONEYUG",
    "amount": "800",
    "message": "Demo Request",
    "customerId": "256778418592",
    "customerPhone": "256778418592",
    "customerEmail": "d-kintu@outlook.com",
    "customerReference": "256778418592",
    "batchId": "Batch001",
    "requestId": "DemoRequest337",
    "metadata": "More information about transaction here"
}

- The Services package contains the logic to generate bearer tokens (that grant authority to the XentePayments API) in the
    TokenHandler class.
- The Services package also contains logic to manipulate the data in the JSON files in the ObjectHandler class.
    It it highly important that the integrator passes the JSON files correctly to the XentePayments instance, with the
    Credentials JSON passed first followed by the Transaction JSON file. This is further shown in a code snippet below.
- The Services package also contains logic to handle the assignment of the various URLs to be called within the
    URLConstants class.
- The Services package has the logic to perform all GET requests from the XentePayments API within the GETRequestClient class.
- The Services package has the logic to perform all POST requests to the XentePayments API within the POSTRequestClient class.

> Invoke an instance of the XentePayments package as follows:
    XentePayments xente = new XentePayments(credentials, transaction);

- From this the integrator can now use the object to perform various methods within the Components package through
    accessing the various component objects already created in the class constructor. Below is how it can be done:

> The integrator can retrieve their account information by using their account ID as shown below.
    String accountID = "256784378515";
    xente.accountsHandler.getAccountByID(accountID);

> The integrator can also check to see which payment providers are available to use on the XentePayments platform.
    xente.paymentProviders.getPaymentProviders();

> The integrator can also create a new transaction
    xente.transactionsHandler.createTransaction();

> The integrator can also search for a specific transaction using the transaction ID.
    String transactionID = "631034D3F96C441085FA7D010ACB7194-256784378515";
    xente.transactionsHandler.getTransactionByID(transactionID);

> Lastly the integrator can search for a transaction using the Request ID.
    String requestID = "0.9351612896255068";
    xente.transactionsHandler.getRequestByID(requestID);