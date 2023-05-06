package com.dgomezt.practicaintegradora.components;

import com.dgomezt.practicaintegradora.utilities.UserForm;
import jakarta.servlet.http.Cookie;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

@Component
public class CookieManager {
    public Cookie createCookie(String name, String value){
        String valueEncoded = UriUtils.encodePath(value, "utf-8");
        return new Cookie(name, valueEncoded);
    }

    public String readEncodedCookie(String value){
        String valueDecoded = UriUtils.decode(value, "utf-8");
        return valueDecoded;
    }

    private static Cookie deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        return cookie;
    }
}
