package com.dgomezt.practicaintegradora.utilities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "custom")
public class ConfProperties {
    public Integer LOCK_DAYS;
    public String COOKIE_LAST_USER;
    public String COOKIE_CONNECTED_USER;
    public String COOKIE_USERS_CONNECTIONS;
    public String CONTENT_CONTAINER;
    public String FRAGMENT_CONTAINER;
    public String SESSION_USER;
    public String SESSION_USER_AUTHENTICATION;
    public String SESSION_ADMIN_USER;
}
