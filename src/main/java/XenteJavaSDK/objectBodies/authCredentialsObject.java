/*
 * Copyright (c) 2019. This software and it's likeness belong to Kintu Declan Trevor.
 * <d-kintu@outlook.com>
 * This software is open for usage and modification by any user.
 * Thank you.
 */

package XenteJavaSDK.objectBodies;

import com.sun.istack.internal.NotNull;

public class authCredentialsObject {
    @NotNull
    private String apiKey, password, mode;

    //Implement Getter and Setter for all Key-Value pairs
    public String getApiKey()
        { return apiKey; }
    public void setApiKey(String apiKey)
        {this.apiKey = apiKey;}

    public String getPassword()
        { return password; }
    public void setPassword(String password)
        { this.password = password; }

    public String getMode()
        { return mode; }
    public void setMode(String mode)
        { this.mode = mode;}

}
