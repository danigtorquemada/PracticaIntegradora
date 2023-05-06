package com.dgomezt.practicaintegradora.utilities;

import lombok.Data;

@Data
public class UserAuthentication {
    private static final String SEPARATOR = "#";
    private String username;
    private String password;
    private boolean logged;

    @Override
    public String toString() {
        return username + SEPARATOR + password + SEPARATOR + logged;
    }

    public UserAuthentication fromString(String userAuthenticationString){
        String[] parts = userAuthenticationString.split(SEPARATOR);

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUsername(parts[0]);
        userAuthentication.setPassword(parts[1]);
        userAuthentication.setLogged(Boolean.parseBoolean(parts[2]));

        return userAuthentication;
    }
}
