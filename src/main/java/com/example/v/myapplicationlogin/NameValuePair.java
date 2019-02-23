package com.example.v.myapplicationlogin;

public class NameValuePair {
    String mName;
    String mValue;
    NameValuePair(String name, String value){
        mName = name;
        mValue = value;
    }
    String getName(){
        return  mName;
    }
    String getValue(){
        return  mValue;
    }
}
