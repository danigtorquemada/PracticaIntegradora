package com.dgomezt.practicaintegradora.components;

import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class CookieManager {
    private static String SEPARATOR_OBJECT = ";";
    private static String SEPARATOR_PROPERTY = ":";

    @Autowired
    ConfProperties confProperties;

    public Cookie saveCookieUsers(Map<String, Integer> userConnections) {
        String value = "";
        for (Map.Entry<String, Integer> entry : userConnections.entrySet()) {
            value += entry.getKey() + SEPARATOR_PROPERTY + entry.getValue() + SEPARATOR_OBJECT;
        }

        return createCookie(confProperties.COOKIE_USERS_CONNECTIONS, value);
    }

    public Map<String, Integer> getUsersFromCookieUser(String value){
        String[] userConnections = value.split(SEPARATOR_OBJECT);

        Map<String, Integer> mapUsers = new HashMap<>();
        for (String userConnection : userConnections) {
            String[] parts = userConnection.split(SEPARATOR_PROPERTY);
            mapUsers.put(parts[0], Integer.valueOf(parts[1]));
        }

        return mapUsers;
    }

    public Cookie createCookie(String name, String value){
        String valueEncoded = URLEncoder.encode(value, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie(name, valueEncoded);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        return cookie;
    }

    public String readEncodedCookie(String value){
        String valueDecoded = URLDecoder.decode(value, StandardCharsets.UTF_8);
        return valueDecoded;
    }

    public Cookie deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }
}
