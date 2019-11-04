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
public class authCredentialsObject {
    //Declare the variables to be used.
    @NotNull
    private String apiKey, password, mode;

    //Implement Getter and Setter for all Key-Value pairs
    public String getApiKey()
        { return apiKey; }

    public String getPassword()
        { return password; }

    public String getMode()
        { return mode; }
}
