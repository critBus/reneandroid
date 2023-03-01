package com.rene.android.reneandroid.LibreriasExternas.sun.security.action;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class GetPropertyAction
    implements PrivilegedAction {

            private String theProp;
            private String defaultVal;

            public GetPropertyAction(String s) {
/*  63*/        theProp = s;
            }

            public GetPropertyAction(String s, String s1) {
/*  74*/        theProp = s;
/*  75*/        defaultVal = s1;
            }

            public String run() {
/*  86*/        String s = System.getProperty(theProp);
/*  87*/        return s != null ? s : defaultVal;
            }

            public static String privilegedGetProperty(String s) {
/* 104*/        if (System.getSecurityManager() == null) {
/* 105*/            return System.getProperty(s);
                } else {
/* 107*/            return (String)AccessController.doPrivileged(new GetPropertyAction(s));
                }
            }

           // public volatile Object run() {
/*  52*/  //      return run();
           // }
}
