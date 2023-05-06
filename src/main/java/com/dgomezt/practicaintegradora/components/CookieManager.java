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
        Cookie cookie = new Cookie(name, valueEncoded);
        cookie.setPath("/");
        return cookie;
    }

    public String readEncodedCookie(String value){
        String valueDecoded = UriUtils.decode(value, "utf-8");
        return valueDecoded;
    }

    public Cookie deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }
}
