package com.globant.knowledgeboostchallenge.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class URLUtilTest {

    static URLUtil urlUtil;

    @BeforeEach
    void setUp() {
        urlUtil= new URLUtil("http://api.openweathermap.org/data/2.5/weather?");
    }

    @Test
    void set() {
        String urlTest = "http://api.openweathermap.org/data/2.5/weather?AppId=18e9633e9829e4d1b05adf1723de2bb1";
        urlUtil.set("AppId", "18e9633e9829e4d1b05adf1723de2bb1");
        assertEquals(urlTest,urlUtil.build());
    }

    @Test
    void setNull() {
        String urlTest = "http://api.openweathermap.org/data/2.5/weather?";
        urlUtil.set(null, "18e9633e9829e4d1b05adf1723de2bb1");
        urlUtil.set("longitud", null);
        assertEquals(urlTest,urlUtil.build());
    }


    @Test
    void replace() {
        String urlTest = "http://api.openweathermap.org/data/2.5/weather?ApplicationId=18e9633e9829e4d1b05adf1723de2bb1";
        urlUtil.set("ApplicationId", "18e9633e9829e4d1b05adf1723de2bb1");
        urlUtil.replace("ApplicationId", "AppId");
        assertEquals(urlTest,urlUtil.build());
    }


    @Test
    void build() {
        String urlTest = "http://api.openweathermap.org/data/2.5/weather?AppId=18e9633e9829e4d1b05adf1723de2bb1";
        urlUtil.set("AppId", "18e9633e9829e4d1b05adf1723de2bb1");
        assertEquals(urlTest,urlUtil.build());
    }
}