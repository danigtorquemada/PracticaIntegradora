package com.dgomezt.practicaintegradora.utilities;

import lombok.Data;

@Data
public class UserAuthentication {
    private static final int MAX_ATTEMPS = 3;

    private String username;
    private String password;
    private int attemps = MAX_ATTEMPS;
}
